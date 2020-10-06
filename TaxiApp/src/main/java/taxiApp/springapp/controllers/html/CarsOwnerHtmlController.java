package taxiApp.springapp.controllers.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.core.CV;
import taxiApp.core.Car;
import taxiApp.core.CarsOwner;
import taxiApp.springapp.services.CarsOwnerService;
import taxiApp.springapp.services.representations.MessageRepresentation;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/owner")
public class CarsOwnerHtmlController {

    private final CarsOwnerService ownerService;

    public CarsOwnerHtmlController(@Autowired CarsOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"front"})
    public String ui(Principal principal, Model model) {
        String login = principal.getName();
        CarsOwner owner = ownerService.getByLogin(login);
        fillModel(owner, model);
        return "Owner";
    }

    @GetMapping({"/cv/{cvId}/nack"})
    public String nackCV(Principal principal, @PathVariable Long cvId, Model model) {
        String login = principal.getName();
        CarsOwner owner = ownerService.getByLogin(login);
        ownerService.nackCV(owner.getId(), cvId);
        fillModel(owner, model);
        return "Owner";
    }

    @PostMapping({"/cv/{cvId}/ack"})
    public String ackCV(Principal principal, @PathVariable Long cvId, HttpServletRequest request, Model model) {
        String login = principal.getName();
        CarsOwner owner = ownerService.getByLogin(login);
        Long carId = Long.parseLong(request.getParameter("carId"));
        ownerService.ackCV(owner.getId(), cvId, carId);
        fillModel(owner, model);
        return "Owner";
    }

    private void fillModel(CarsOwner owner, Model model) {
        List<MessageRepresentation> messages = ownerService.getMessagesRepr(owner);
        List<Car> cars = ownerService.getCars(owner);
        List<CV> cvs = ownerService.getCVs();
        model.addAttribute("name", owner.getPersonInfo().toString());
        model.addAttribute("ownerMessages", messages);
        model.addAttribute("cars", cars);
        model.addAttribute("CVs", cvs);
    }
}
