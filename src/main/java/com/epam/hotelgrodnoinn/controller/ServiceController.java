package com.epam.hotelgrodnoinn.controller;

import com.epam.hotelgrodnoinn.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ServiceController extends BaseController {

    @RequestMapping("/page")
    public String switchLanguage(@RequestParam(name = "name") String pageName, Model model) {

        if (pageName.equals("signup")) {
            model.addAttribute("registration", new User());
        }
        if (pageName.equals("login")) {
            model.addAttribute("login", new User());
        }
        return openPage(pageName);
    }

    @RequestMapping("/command")
    public String doCommand(@RequestParam(name = "name") String commandName) {

        log.info("the following command detected -> {}", commandName);
        return "forward:/" + commandName;
    }
}
