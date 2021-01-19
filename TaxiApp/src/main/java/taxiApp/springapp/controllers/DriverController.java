package taxiApp.springapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import taxiApp.Exceptions.NoEntityException;
import taxiApp.Exceptions.NotLoginException;
import taxiApp.core.Driver;
import taxiApp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.core.Order;
import taxiApp.springapp.services.DriverService;
import taxiApp.springapp.services.representations.MessageRepresentation;
import taxiApp.springapp.services.representations.OrderRepresentation;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(@Autowired DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/messages")
    @ResponseBody
    List<MessageRepresentation> getMessages(Principal principal) {
        checkPrincipal(principal);
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        return driverService.getMessagesRepr(driver);
    }

    @GetMapping("/currentOrder")
    @ResponseBody
    Order getCurrentOrder(Principal principal) {
        checkPrincipal(principal);
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        return driverService.getCurrentOrder(driver);
    }

    @GetMapping("/{isAck}")
    @ResponseBody
    void ackOrder(Principal principal, @PathVariable String isAck) {
        checkPrincipal(principal);
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        driverService.processOrder(driver, isAck.equals("ACK"));
    }

    @GetMapping("/finish/{idOrder}")
    @ResponseBody
    void finishOrder(Principal principal, @PathVariable Long idOrder) throws NoEntityException {
        checkPrincipal(principal);
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        driverService.finishOrder(driver, idOrder);
    }

    @GetMapping("/set/{idOrder}")
    @ResponseBody
    OrderRepresentation setOrder(Principal principal, @PathVariable Long idOrder) throws NoEntityException {
        checkPrincipal(principal);
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        driverService.setOrder(driver, idOrder);
        return new OrderRepresentation(driverService.getOrder(idOrder));
    }

    private void checkPrincipal(Principal principal) {
        if (null == principal) {
            throw new NotLoginException();
        }
    }
}
