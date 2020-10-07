package taxiApp.springapp.controllers.html;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import taxiApp.Exceptions.NoEntityException;
import taxiApp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.springapp.services.DriverService;
import taxiApp.springapp.services.representations.MessageRepresentation;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/driver")
public class DriverHtmlController {

    private final DriverService driverService;

    public DriverHtmlController(@Autowired DriverService driverService) {
        this.driverService = driverService;
    }

    @RequestMapping({"/front"})
    public String ui(Principal principal, Model model) {
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        fillModel(driver, model);
        return "Driver";
    }

    @GetMapping({"/{isAck}"})
    public String ackOrder(Principal principal, @PathVariable String isAck, Model model) {
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        driverService.processOrder(driver, isAck.equals("ACK"));
        fillModel(driver, model);
        return "Driver";
    }

    @PostMapping("/finish")
    public String finishOrder(Principal principal, HttpServletRequest request, Model model) {
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        Long idOrder = Long.parseLong(request.getParameter("idOrder"));
        try {
            driverService.finishOrder(driver, idOrder);
        } catch (NoEntityException e) {
            return handleError(driver, model, e.getMessage());
        }
        fillModel(driver, model);
        model.addAttribute("orderFinished", true);
        return "Driver";
    }

    @PostMapping("/set")
    public String setOrder(Principal principal, HttpServletRequest request, Model model) {
        String login = principal.getName();
        Driver driver = driverService.getByLogin(login);
        if (request.getParameter("idOrder").isEmpty())
            return handleError(driver, model, "Order id is empty!");
        Long idOrder = Long.parseLong(request.getParameter("idOrder"));
        try {
            driverService.setOrder(driver, idOrder);
        } catch (NoEntityException e) {
            return handleError(driver, model, e.getMessage());
        }
        fillModel(driver, model);
        model.addAttribute("orderSet", true);
        return "Driver";
    }

    private void fillModel(Driver driver, Model model) {
        List<MessageRepresentation> messages = driverService.getMessagesRepr(driver);
        Order order = driverService.getOrder(driver.getId());
        model.addAttribute("name", driver.getPersonInfo().toString());
        model.addAttribute("driverMessages", messages);
        model.addAttribute("currentOrder", order);
        model.addAttribute("driversCar", driver.getCar().toString());
    }

    private String handleError(Driver driver, Model model, String errorMsg) {
        fillModel(driver, model);
        model.addAttribute("errorMsg", errorMsg);
        return "Driver";
    }

}
