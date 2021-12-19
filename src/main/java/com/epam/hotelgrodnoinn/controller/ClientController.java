package com.epam.hotelgrodnoinn.controller;

import com.epam.hotelgrodnoinn.entity.User;
import com.epam.hotelgrodnoinn.service.impl.ClientServiceImpl;
import com.epam.hotelgrodnoinn.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Controller
public class ClientController extends BaseController {

    public static final String REGEX = "^(\\d+(\\.\\d+)?)$";

    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping(value = "/clientcabinet")
    public String goClientCabinet(HttpServletRequest request, Model model) {

        User loggedUser = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("clientRequests", clientService.getClientRequest(loggedUser));
        request.getSession().setAttribute("clientOrders", clientService.getClientOrder(loggedUser));
        return openPage("clientcabinet");
    }

    @RequestMapping(value = "/account")
    public String goAccount(HttpServletRequest request) {

        User loggedUser = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", clientService.getClient(loggedUser.getUserID()));
        return openPage("account");
    }

    @RequestMapping(value = "/top_up")
    public String topUp(
            @RequestParam(name = "chargeAmount") String chargeAmount,
            HttpServletRequest request) {

        if (!Pattern.matches(REGEX, chargeAmount)) {
            request.setAttribute("error", true);
            return openPage("chargeaccount");
        }
        User loggedUser = (User) request.getSession().getAttribute("user");
        clientService.topUp(loggedUser.getUserID(), Double.valueOf(chargeAmount));
        request.setAttribute("charged", true);
        return "forward:/account";
    }

    @RequestMapping(value = "/request_logged_user")
    public String doRequest(@RequestParam(name = "persons") String persons,
                            @RequestParam(name = "roomClass") String roomClass,
                            @RequestParam(name = "datefilter") String dateFilter,
                            HttpServletRequest request) {

        User loggedUser = (User) request.getSession().getAttribute("user");
        clientService.insertRequest(loggedUser, persons, roomClass, dateFilter);
        return "forward:/clientcabinet";
    }

    @RequestMapping(value = "/cancel_request")
    public String cancelRequest(@RequestParam(name = "requestID") String requestID) {

        clientService.cancelRequest(requestID);
        return "forward:/clientcabinet";
    }

    @RequestMapping(value = "/invoice")
    public String prepareInvoice(@RequestParam(value = "orderID") String orderID,
                                 @RequestParam(value = "requestID") String requestID,
                                 @RequestParam(value = "roomID") String roomID,
                                 @RequestParam(value = "roomClass") String roomClass,
                                 @RequestParam(value = "dateFrom") String dateFrom,
                                 @RequestParam(value = "dateTo") String dateTo,
                                 HttpServletRequest request) {


        request.getSession().setAttribute("orderID", orderID);
        request.getSession().setAttribute("requestID", requestID);
        request.getSession().setAttribute("roomID", roomID);
        request.getSession().setAttribute("roomClass", roomClass);
        request.getSession().setAttribute("dateFrom", dateFrom);
        request.getSession().setAttribute("dateTo", dateTo);
        Double roomPrice = orderService.getRoomPrice(Integer.parseInt(roomID));
        request.getSession().setAttribute("roomPrice", roomPrice);

        User loggedUser = (User) request.getSession().getAttribute("user");
        request.setAttribute("notEnoughMoney", loggedUser.getAccount() < roomPrice);
        return openPage("invoice");
    }

    @RequestMapping(value = "/pay")
    public String doPay(@RequestParam(value = "clientID") String clientID,
                        @RequestParam(value = "orderID") String orderID,
                        @RequestParam(value = "requestID") String requestID,
                        @RequestParam(value = "roomID") String roomID,
                        @RequestParam(value = "roomPrice") String roomPrice,
                        HttpServletRequest request) {

        orderService.payInvoice(
                Long.parseLong(clientID),
                Integer.parseInt(orderID),
                Integer.parseInt(requestID),
                Integer.parseInt(roomID),
                Double.parseDouble(roomPrice));
        request.setAttribute("paid", true);
        return "forward:/account";
    }
}
