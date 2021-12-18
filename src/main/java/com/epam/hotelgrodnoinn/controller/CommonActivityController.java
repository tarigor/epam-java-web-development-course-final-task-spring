package com.epam.hotelgrodnoinn.controller;

import com.epam.hotelgrodnoinn.entity.User;
import com.epam.hotelgrodnoinn.service.impl.InputValidationImpl;
import com.epam.hotelgrodnoinn.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@Slf4j
public class CommonActivityController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private InputValidationImpl inputValidation;

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
            return "login";
        }
    }

    @PostMapping("/loginProcessForm")
    public String doLogin(@Valid @ModelAttribute("login") User user, BindingResult bindingResult, HttpSession session, Model model) {

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
                return "redirect:/clientcabinet";
            } else {
                return "forward:/admin/cabinet";
            }
        } else {
            log.info("error while login -> {}", result);
            model.addAttribute("errorWhileLogin", true);
            model.addAttribute("errorWhileLoginMessage", result);
            return "login";
        }
    }

    @GetMapping("/login")
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
}
