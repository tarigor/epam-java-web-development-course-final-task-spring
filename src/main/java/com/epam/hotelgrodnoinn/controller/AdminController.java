package com.epam.hotelgrodnoinn.controller;

import com.epam.hotelgrodnoinn.entity.ClientRequestView;
import com.epam.hotelgrodnoinn.entity.RoomData;
import com.epam.hotelgrodnoinn.entity.RoomView;
import com.epam.hotelgrodnoinn.service.IAdminService;
import com.epam.hotelgrodnoinn.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController extends BaseController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoomService roomService;

    @RequestMapping(value = "admincabinet")
    public String goAdminCabinet(HttpServletRequest request) {

        request.getSession().setAttribute("clientRequests", adminService.getAllRequestsForAdmin());
        request.getSession().setAttribute("clientOrders", adminService.getAllOrders());
        return openPage("admincabinet");
    }

    @RequestMapping(value = "request_handling")
    public String doHandling(@RequestParam("requestID") String requestID,
                             @RequestParam("email") String email,
                             @RequestParam("dateFrom") String dateFrom,
                             @RequestParam("dateTo") String dateTo,
                             HttpServletRequest request) {

        ClientRequestView clientRequest = adminService.getClientRequest(requestID, email);
        List<RoomView> roomArrayList = roomService.getFreeRooms(dateFrom, dateTo);
        List<RoomData> roomsData = roomService.getRoomsData();
        request.getSession().setAttribute("clientRequest", clientRequest);
        request.getSession().setAttribute("roomArrayList", roomArrayList);
        request.getSession().setAttribute("dateFrom", dateFrom);
        request.getSession().setAttribute("dateTo", dateTo);
        request.getSession().setAttribute("roomsData", roomsData);
        return openPage("roomslist");
    }

    @RequestMapping(value = "send_invoice")
    public String sendInvoice(
            @RequestParam(name = "singleRoomsSelected", required = false) String[] singleRoomsSelected,
            @RequestParam(name = "doubleRoomsSelected", required = false) String[] doubleRoomsSelected,
            @RequestParam(name = "suiteRoomsSelected", required = false) String[] suiteRoomsSelected,
            @RequestParam(name = "deluxeRoomsSelected", required = false) String[] deluxeRoomsSelected,
            @RequestParam(name = "dateFrom") String dateFrom,
            @RequestParam(name = "dateTo") String dateTo,
            @RequestParam(name = "clientID") String clientID,
            @RequestParam(name = "requestID") String requestID
    ) {

        adminService.insertNewOrder(clientID, requestID, singleRoomsSelected, doubleRoomsSelected, suiteRoomsSelected, deluxeRoomsSelected, dateFrom, dateTo);
        return "redirect:/admincabinet";
    }

    @RequestMapping(value = "reject")
    public String rejectRequest(@RequestParam(value = "request")String requestID){

        adminService.rejectRequest(Integer.parseInt(requestID));
        return "redirect:/admincabinet";
    }
}
