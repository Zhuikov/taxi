package taxiApp.springapp.controllers;

import taxiApp.core.Driver;
import taxiApp.core.Manager;
import taxiApp.core.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.springapp.services.ManagerService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

//    @GetMapping("/{id}")
//    Manager getById(@PathVariable Long id) { return managerService.getById(id); }

    @GetMapping("/messages")
    List<Message> getMessages(Principal principal) {
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        return managerService.getMessages(manager);
    }

    @GetMapping("/drivers")
    List<Driver> getDrivers() {
        return managerService.getDrivers();
    }

    @GetMapping("/order/{orderId}/{isAck}")
    void sendAnswerToClient(Principal principal, @PathVariable Long orderId, @PathVariable String isAck) {
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        managerService.sendAnswerToClient(manager.getId(), orderId, isAck.equals("ACK"));
    }

    @GetMapping("/driver/{driverId}/{isAck}")
    void sendAnswerToDriver(Principal principal, @PathVariable Long driverId, @PathVariable String isAck) {
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        managerService.sendAnswerToDriver(manager.getId(), driverId, isAck.equals("ACK"));
    }

    @GetMapping("/order/{orderId}/driver/{driverId}")
    void sendOrderToDriver(Principal principal, @PathVariable Long orderId, @PathVariable Long driverId) {
        String login = principal.getName();
        Manager manager = managerService.getByLogin(login);
        managerService.sendOrderToDriver(manager.getId(), orderId, driverId);
    }

    @GetMapping("/activate/{driverId}")
    void activateDriver(@PathVariable Long driverId) {
        managerService.activateDriver(driverId);
    }

}
