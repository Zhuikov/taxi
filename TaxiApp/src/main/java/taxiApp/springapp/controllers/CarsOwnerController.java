package taxiApp.springapp.controllers;

import taxiApp.Exceptions.CarIsUsingException;
import taxiApp.Exceptions.NoEntityException;
import taxiApp.core.Car;
import taxiApp.core.CarsOwner;
import taxiApp.core.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.springapp.services.CarsOwnerService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/owner")
public class CarsOwnerController {

    private final CarsOwnerService ownerService;

    public CarsOwnerController(CarsOwnerService ownerService) {
        this.ownerService = ownerService;
    }

//    @GetMapping("/{id}")
//    CarsOwner getById(@PathVariable Long id) { return ownerService.getById(id); }
//
//    @GetMapping("/messages")
//    List<Message> getMessages(Principal principal) {
//        String login = principal.getName();
//        CarsOwner owner = ownerService.getByLogin(login);
//        return ownerService.getMessages(owner);
//    }
//
//    @GetMapping("/cars")
//    List<Car> getCars(Principal principal) {
//        String login = principal.getName();
//        CarsOwner owner = ownerService.getByLogin(login);
//        return ownerService.getCars(owner);
//    }

//    @GetMapping("/cv/{cvId}/nack")
//    void nackCV(Principal principal, @PathVariable Long cvId) {
//        String login = principal.getName();
//        CarsOwner owner = ownerService.getByLogin(login);
//        ownerService.nackCV(owner.getId(), cvId);
//    }

    @GetMapping("/cv/{cvId}/ack/{carId}")
    void ackCV(Principal principal, @PathVariable Long cvId, @PathVariable Long carId) throws CarIsUsingException, NoEntityException {
        String login = principal.getName();
        CarsOwner owner = ownerService.getByLogin(login);
        ownerService.ackCV(owner, cvId, carId);
    }

}
