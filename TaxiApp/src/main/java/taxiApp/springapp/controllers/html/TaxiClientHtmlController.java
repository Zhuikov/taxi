package taxiApp.springapp.controllers.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.core.*;
import taxiApp.springapp.services.representations.MessageRepresentation;
import taxiApp.springapp.services.TaxiClientService;
import taxiApp.springapp.services.representations.OrderRepresentation;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/client")
public class TaxiClientHtmlController {

    private final TaxiClientService clientService;

    public TaxiClientHtmlController(@Autowired TaxiClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping({"/front"})
    public String ui(Principal principal, Model model) {
        String login = principal.getName();
        TaxiClient client = clientService.getByLogin(login);
        fillModel(client, model);
        return "TaxiClient";
    }

    @PostMapping({"/order"})
    public String addOrder(Principal principal, Model model, HttpServletRequest request) {
        String login = principal.getName();
        TaxiClient client = clientService.getByLogin(login);
        if (request.getParameter("srcAddress").isEmpty()) {
            return handleError(client, model, "Fill source address!");
        }
        if (request.getParameter("dstAddress").isEmpty()) {
            return handleError(client, model, "Fill destination address!");
        }
        Order order = new Order(request.getParameter("srcAddress"), request.getParameter("dstAddress"), client);
        clientService.addOrder(order);
        fillModel(client, model);
        return "TaxiClient";
    }

    @PostMapping({"/cv"})
    public String addCV(Principal principal, Model model, HttpServletRequest request) {
        String login = principal.getName();
        TaxiClient client = clientService.getByLogin(login);
        if (request.getParameter("cv_name").isEmpty() || request.getParameter("cv_surname").isEmpty()
                || request.getParameter("cv_phone").isEmpty() || request.getParameter("cv_exp").isEmpty())
            return handleError(client, model, "Fill all CV fields!");
        CV cv = new CV(client.getId(), request.getParameter("cv_name"), request.getParameter("cv_surname"),
                request.getParameter("cv_phone"), Integer.parseInt(request.getParameter("cv_exp")), false);
        clientService.addCV(cv);
        fillModel(client, model);
        model.addAttribute("cvSuccess", true);
        return "TaxiClient";
    }

    private void fillModel(TaxiClient client, Model model) {
        List<MessageRepresentation> messages = clientService.getMessagesRepr(client);
        List<OrderRepresentation> orders = clientService.getClientsOrders(client);
        model.addAttribute("name", client.getPersonInfo().toString());
        model.addAttribute("clientMessages", messages);
        model.addAttribute("clientOrders", orders);
    }

    private String handleError(TaxiClient client, Model model, String errorMsg) {
        fillModel(client, model);
        model.addAttribute("errorMsg", errorMsg);
        return "TaxiClient";
    }
}
