package taxiApp.springapp.controllers;

import taxiApp.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import taxiApp.springapp.services.TaxiClientService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/client")
public class TaxiClientController {

    private final TaxiClientService clientService;

    public TaxiClientController(TaxiClientService clientService) {
        this.clientService = clientService;
    }

//    @GetMapping("/{id}")
//    TaxiClient getById(@PathVariable Long id) { return clientService.getById(id); }

    @GetMapping("/messages")
    List<Message> getMessages(Principal principal) {
        String login = principal.getName();
        TaxiClient client = clientService.getByLogin(login);
        return clientService.getMessages(client);
    }

    @PostMapping("/order")
    Order addOrder(@RequestBody Order order) {
        return clientService.addOrder(order);
    }

    @PostMapping("/cv")
    CV addCV(@RequestBody CV cv) {
        return clientService.addCV(cv);
    }
}
