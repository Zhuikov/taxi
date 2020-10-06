package taxiApp.springapp.controllers;

import taxiApp.core.Driver;
import taxiApp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.springapp.services.DriverService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(@Autowired DriverService driverService) {
        this.driverService = driverService;
    }

//    @GetMapping("/{id}")
//    Driver getById(@PathVariable Long id) { return driverService.getById(id); }

    @GetMapping("/messages")
    List<Message> getMessages(Principal principal) {
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        return driverService.getMessages(driver);
    }

    @GetMapping("/{isAck}")
    void ackOrder(Principal principal, @PathVariable String isAck) {
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        driverService.processOrder(driver, isAck.equals("ACK"));
    }

    @GetMapping("/finish/{idOrder}")
    void finishOrder(Principal principal, @PathVariable Long idOrder) {
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        driverService.finishOrder(driver, idOrder);
    }

    @GetMapping("/set/{idOrder}")
    void setOrder(Principal principal, @PathVariable Long idOrder) {
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        driverService.setOrder(driver, idOrder);
    }

}
