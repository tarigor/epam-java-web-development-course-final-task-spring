package com.epam.hotelgrodnoinn.controller;

import com.epam.hotelgrodnoinn.entity.User;
import com.epam.hotelgrodnoinn.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientController extends BaseController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping(value = "/clientcabinet")
    public String goClientCabinet(HttpServletRequest request, Model model) {

        User loggedUser = (User) request.getSession().getAttribute("user");
        model.addAttribute("clientRequests", clientService.getClientRequest(loggedUser));
        model.addAttribute("clientOrders", clientService.getClientOrder(loggedUser));
        return openPage("clientcabinet");
    }
}
