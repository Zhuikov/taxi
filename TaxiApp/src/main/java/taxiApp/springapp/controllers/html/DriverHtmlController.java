package taxiApp.springapp.controllers.html;

import taxiApp.core.Driver;
import taxiApp.core.Message;
import taxiApp.core.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.springapp.services.DriverService;

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
        List<Message> messages = driverService.getMessages(driver);
        Order order = driverService.getOrder(driver.getId());
        model.addAttribute("name", driver.getPersonInfo().toString());
        model.addAttribute("driverMessages", messages);
        model.addAttribute("currentOrder", order);
        return "Driver";
    }

}
