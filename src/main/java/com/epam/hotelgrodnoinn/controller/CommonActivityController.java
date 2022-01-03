package com.epam.hotelgrodnoinn.controller;

import com.epam.hotelgrodnoinn.entity.User;
import com.epam.hotelgrodnoinn.service.IUserService;
import com.epam.hotelgrodnoinn.service.IInputValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@Slf4j
public class CommonActivityController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IInputValidation inputValidation;

    @GetMapping("/")
    public String showHome() {
        return openPage("index");
    }

    @PostMapping("/registrationProcessForm")
    public String doRegistration(@Valid @ModelAttribute("registration") User user, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors() || !inputValidation.validatePasswordTwice(user.getPassword(), user.getRepeatedPassword())) {
            log.warn("input fault while registration detected");
            ArrayList<String> fieldErrorList = inputValidation.doValidation(bindingResult);
            request.setAttribute("fieldErrorList", fieldErrorList);
            return "signup";
        } else {
            userService.doNewUserRegistration(user);
            return "redirect:/login";
        }
    }

    @PostMapping("/loginProcessForm")
    public String doLogin(
            @Valid @ModelAttribute("login") User user,
            @RequestParam(name = "loginAndCompleteRequest", required = false) String loginAndCompleteRequest,
            BindingResult bindingResult, HttpSession session, Model model) {

        if (bindingResult.hasErrors()) {
            log.warn("input fault while login detected");
            ArrayList<String> fieldErrorList = inputValidation.doValidation(bindingResult);
            model.addAttribute("fieldErrorList", fieldErrorList);
            return "login";
        }

        Object result = userService.checkUserExisting(user);

        if (result instanceof User) {
            User loggedUser = (User) result;
            session.setAttribute("user", loggedUser);
            if (loggedUser.getUserType().equals("CLIENT")) {
                if (Boolean.parseBoolean(loginAndCompleteRequest)) {
                    return "forward:/request_logged_user";
                }
                return "redirect:/clientcabinet";
            } else {
                return "redirect:/admincabinet";
            }
        } else {
            log.info("error while login -> {}", result);
            model.addAttribute("errorWhileLogin", true);
            model.addAttribute("errorWhileLoginMessage", result);
            return "login";
        }
    }

    @RequestMapping("/login")
    public String doLogin(Model model) {

        model.addAttribute("login", new User());
        return "login";
    }

    @RequestMapping("/logout")
    public String doLogOut(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        session.invalidate();
        model.addAttribute("login", new User());
        return "redirect:/login";
    }

    @RequestMapping("/request")
    public String doRequest(@RequestParam(name = "persons") String persons,
                            @RequestParam(name = "roomClass") String roomClass,
                            @RequestParam(name = "datefilter") String dateFilter,
                            HttpServletRequest request) {

        request.setAttribute("persons", persons);
        request.setAttribute("roomClass", roomClass);
        request.setAttribute("datefilter", dateFilter);
        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser == null) {
            request.setAttribute("loginAndCompleteRequest", true);
            return "forward:/login";
        } else {
            return "forward:/request_logged_user";
        }
    }
}
