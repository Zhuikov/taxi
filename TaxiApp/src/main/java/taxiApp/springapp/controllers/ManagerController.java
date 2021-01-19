package taxiApp.springapp.controllers;

import org.springframework.web.bind.annotation.ResponseBody;
import taxiApp.Exceptions.NoEntityException;
import taxiApp.Exceptions.NotLoginException;
import taxiApp.core.Manager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.core.TaxiClient;
import taxiApp.springapp.services.ManagerService;
import taxiApp.springapp.services.representations.DriverRepresentation;
import taxiApp.springapp.services.representations.MessageRepresentation;
import taxiApp.springapp.services.representations.OrderRepresentation;

import java.security.Principal;
import java.sql.Driver;
import java.util.List;

@Controller
@RequestMapping("/api/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/orders")
    @ResponseBody
    List<OrderRepresentation> getOrders(Principal principal) {
        checkPrincipal(principal);
        return managerService.getOrders();
    }

    @GetMapping("/messages")
    @ResponseBody
    List<MessageRepresentation> getMessages(Principal principal) {
        checkPrincipal(principal);
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        return managerService.getMessagesRepr(manager);
    }

    @GetMapping("/drivers")
    @ResponseBody
    List<DriverRepresentation> getDrivers(Principal principal) {
        checkPrincipal(principal);
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        return managerService.getDrivers();
    }

    @GetMapping("/order/{orderId}/{isAck}")
    @ResponseBody
    void sendAnswerToClient(Principal principal, @PathVariable Long orderId, @PathVariable String isAck) throws NoEntityException {
        checkPrincipal(principal);
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        managerService.sendAnswerToClient(manager, orderId, isAck.equals("ACK"));
    }

    @GetMapping("/driver/{driverId}/{isAck}")
    @ResponseBody
    void sendAnswerToDriver(Principal principal, @PathVariable Long driverId, @PathVariable String isAck) throws NoEntityException {
        checkPrincipal(principal);
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        managerService.sendAnswerToDriver(manager, driverId, isAck.equals("ACK"));
    }

    @GetMapping("/order/{orderId}/driver/{driverId}")
    @ResponseBody
    void sendOrderToDriver(Principal principal, @PathVariable Long orderId, @PathVariable Long driverId) throws NoEntityException {
        checkPrincipal(principal);
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        managerService.sendOrderToDriver(manager, orderId, driverId);
    }

    @GetMapping("/activate/{driverId}")
    @ResponseBody
    void activateDriver(Principal principal, @PathVariable Long driverId) throws NoEntityException {
        checkPrincipal(principal);
        managerService.activateDriver(driverId);
    }

    private void checkPrincipal(Principal principal) {
        if (null == principal) {
            throw new NotLoginException();
        }
    }

}
