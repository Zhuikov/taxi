package taxiApp.springapp.controllers.html;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.Exceptions.NoEntityException;
import taxiApp.core.Driver;
import taxiApp.core.Manager;
import taxiApp.springapp.services.representations.DriverRepresentation;
import taxiApp.springapp.services.ManagerService;
import taxiApp.springapp.services.representations.MessageRepresentation;
import taxiApp.springapp.services.representations.OrderRepresentation;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerHtmlController {

    private final ManagerService managerService;

    public ManagerHtmlController(@Autowired ManagerService managerService) {
        this.managerService = managerService;
    }

    @RequestMapping("/front")
    public String ui(Principal principal, Model model) {
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        fillModel(manager, model);
        return "Manager";
    }

    @PostMapping("/client/{isAck}")
    public String sendAnswerToClient(Principal principal, HttpServletRequest request, @PathVariable String isAck, Model model) {
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        if (request.getParameter("orderId").isEmpty())
            return handleError(manager, model, "Order id is empty!");
        Long orderId = Long.parseLong(request.getParameter("orderId"));
        try {
            managerService.sendAnswerToClient(manager, orderId, isAck.equals("ACK"));
        } catch (NoEntityException e) {
            return handleError(manager, model, e.getMessage());
        }
        fillModel(manager, model);
        return "Manager";
    }

    @PostMapping("/driver/{isAck}")
    public String sendAnswerToDriver(Principal principal, HttpServletRequest request, @PathVariable String isAck, Model model) {
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        if (request.getParameter("driverId").isEmpty())
            return handleError(manager, model, "Driver id is empty!");
        Long driverId = Long.parseLong(request.getParameter("driverId"));
        try {
            managerService.sendAnswerToDriver(manager, driverId, isAck.equals("ACK"));
        } catch (NoEntityException e) {
            return handleError(manager, model, e.getMessage());
        }
        fillModel(manager, model);
        return "Manager";
    }

    @PostMapping("/order/driver")
    public String sendOrderToDriver(Principal principal, HttpServletRequest request, Model model) {
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        if (request.getParameter("sendDriverId").isEmpty())
            return handleError(manager, model, "Driver id is empty!");
        if (request.getParameter("sendOrderId").isEmpty())
            return handleError(manager, model, "Order id is empty!");
        Long driverId = Long.parseLong(request.getParameter("sendDriverId"));
        Long orderId = Long.parseLong(request.getParameter("sendOrderId"));
        try {
            managerService.sendOrderToDriver(manager, orderId, driverId);
        } catch (NoEntityException e) {
            return handleError(manager, model, e.getMessage());
        }
        fillModel(manager, model);
        return "Manager";
    }

    @PostMapping("/activate")
    public String activateDriver(Principal principal, HttpServletRequest request, Model model) {
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        if (request.getParameter("activateDriverId").isEmpty())
            return handleError(manager, model, "Driver id for activation is empty!");
        Long driverId = Long.parseLong(request.getParameter("activateDriverId"));
        try {
            managerService.activateDriver(driverId);
        } catch (NoEntityException e) {
            return handleError(manager, model, e.getMessage());
        }
        model.addAttribute("activationSuccess", true);
        fillModel(manager, model);
        return "Manager";
    }

    private void fillModel(Manager manager, Model model) {
        List<MessageRepresentation> messages = managerService.getMessagesRepr(manager);
        List<DriverRepresentation> drivers = managerService.getDrivers();
        List<OrderRepresentation> orders = managerService.getOrders();
        model.addAttribute("name", manager.getPersonInfo().toString());
        model.addAttribute("managerMessages", messages);
        model.addAttribute("driverList", drivers);
        model.addAttribute("orderList", orders);

        for (Driver d : managerService.getAllDrivers()) {
            System.out.println(d.getId() +  " " + d.getPersonInfo() + " " + d.getCar() + " " + d.isActive() + " " + d.getLogin());
        }
    }

    private String handleError(Manager manager, Model model, String errorMsg) {
        fillModel(manager, model);
        model.addAttribute("errorMsg", errorMsg);
        return "Manager";
    }
}
