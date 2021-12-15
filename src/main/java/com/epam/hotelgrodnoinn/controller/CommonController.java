package com.epam.hotelgrodnoinn.controller;

import com.epam.hotelgrodnoinn.entity.User;
import com.epam.hotelgrodnoinn.service.impl.InputValidationImpl;
import com.epam.hotelgrodnoinn.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class CommonController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private InputValidationImpl inputValidation;

    @GetMapping("/")
    public String showHome() {
        return openPage("index");
    }

    @PostMapping("/processForm")
    public String doRegistration(@Valid @ModelAttribute("registration") User user, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors() || !inputValidation.validatePasswordTwice(user.getPassword(),user.getRepeatedPassword())) {
            System.out.println("input fault detected");
            ArrayList<String> fieldErrorList = inputValidation.doValidation(bindingResult);
            fieldErrorList.forEach(System.out::println);
            request.setAttribute("fieldErrorList", fieldErrorList);
            return "signup";
        }else {
            userService.doNewUserRegistration(user);
            return "login";
        }
    }

    @GetMapping("/page")
    public String switchLanguage(@RequestParam(name = "name") String pageName, Model model) {
        if (pageName.equals("signup")) {
            System.out.println("in signup");
            model.addAttribute("registration", new User());
        }
        return openPage(pageName);
    }

    @PostMapping("/command")
    public String doCommand(@RequestParam(name = "name") String commandName) {
        System.out.println("command->" + commandName);
        System.out.println("in post login");

        return "forward:/" + commandName;
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam(name = "email") String email,
                          @RequestParam(name = "password") String password) {

        return "forward:/cabinet";
    }


}
