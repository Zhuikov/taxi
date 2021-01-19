package taxiApp.springapp.controllers;

import taxiApp.Exceptions.NotLoginException;
import taxiApp.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import taxiApp.springapp.services.TaxiClientService;
import taxiApp.springapp.services.representations.MessageRepresentation;
import taxiApp.springapp.services.representations.OrderRepresentation;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/client")
public class TaxiClientController {

    private final TaxiClientService clientService;

    public TaxiClientController(TaxiClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/order")
    @ResponseBody
    Order addOrder(Principal principal, @RequestBody Order order) {
        checkPrincipal(principal);
        String login = principal.getName();
        TaxiClient client = clientService.getByLogin(login);
        Order newOrder = new Order(order.getSrcAddress(), order.getDstAddress(), client);
        Order order1 = clientService.addOrder(newOrder);
        for (OrderRepresentation o : clientService.getClientsOrders(client)) {
            System.out.println(o.getDstAddress() + " " + o.getSrcAddress() + " " + o.getId());
        }
        return order1;
    }

    @PostMapping("/cv")
    @ResponseBody
    CV addCV(Principal principal, @RequestBody CV cv) {
        checkPrincipal(principal);
        String login = principal.getName();
        TaxiClient client = clientService.getByLogin(login);
        CV newCv = new CV(client.getId(), cv.getName(), cv.getSurname(), cv.getPhone(), cv.getExperience(), false);
        return clientService.addCV(newCv);
    }

    @GetMapping("/messages")
    @ResponseBody
    List<MessageRepresentation> getMessages(Principal principal) {
        checkPrincipal(principal);
        String login = principal.getName();
        TaxiClient client = clientService.getByLogin(login);
        return clientService.getMessagesRepr(client);
    }

    @GetMapping("/orders")
    @ResponseBody
    List<OrderRepresentation> getOrders(Principal principal) {
        checkPrincipal(principal);
        String login = principal.getName();
        TaxiClient client = clientService.getByLogin(login);
        return clientService.getClientsOrders(client);
    }

    private void checkPrincipal(Principal principal) {
        if (null == principal) {
            throw new NotLoginException();
        }
    }
}
