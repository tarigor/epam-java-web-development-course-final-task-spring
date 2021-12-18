package com.epam.hotelgrodnoinn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "admin")
public class AdminController {

    @RequestMapping(value = "cabinet")
    public String goAdminCabinet() {
        return "admincabinet";
    }
}
