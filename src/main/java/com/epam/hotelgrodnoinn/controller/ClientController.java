package com.epam.hotelgrodnoinn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientController extends BaseController {

    @PostMapping("/cabinet")
    public String goClientCabinet() {
        System.out.println("in /client/cabinet");
        return openPage( "clientcabinet");
    }
}
