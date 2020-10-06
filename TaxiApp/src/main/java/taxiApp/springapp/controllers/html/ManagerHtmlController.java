package taxiApp.springapp.controllers.html;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.core.Manager;
import taxiApp.core.Order;
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
        Long orderId = Long.parseLong(request.getParameter("orderId"));
        managerService.sendAnswerToClient(manager, orderId, isAck.equals("ACK"));
        fillModel(manager, model);
        return "Manager";
    }

    @PostMapping("/driver/{isAck}")
    public String sendAnswerToDriver(Principal principal, HttpServletRequest request, @PathVariable String isAck, Model model) {
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        Long driverId = Long.parseLong(request.getParameter("driverId"));
        managerService.sendAnswerToDriver(manager, driverId, isAck.equals("ACK"));
        fillModel(manager, model);
        return "Manager";
    }

    @PostMapping("/order/driver")
    public String sendOrderToDriver(Principal principal, HttpServletRequest request, Model model) {
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        Long orderId = Long.parseLong(request.getParameter("sendOrderId"));
        Long driverId = Long.parseLong(request.getParameter("sendDriverId"));
        managerService.sendOrderToDriver(manager, orderId, driverId);
        fillModel(manager, model);
        return "Manager";
    }

    @PostMapping("/activate")
    public String activateDriver(Principal principal, HttpServletRequest request, Model model) {
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        Long driverId = Long.parseLong(request.getParameter("activateDriverId"));
        managerService.activateDriver(driverId);
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
    }
}
