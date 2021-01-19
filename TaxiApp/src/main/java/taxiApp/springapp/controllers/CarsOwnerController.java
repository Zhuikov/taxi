package taxiApp.springapp.controllers;

import org.springframework.web.bind.annotation.ResponseBody;
import taxiApp.Exceptions.CarIsUsingException;
import taxiApp.Exceptions.NoEntityException;
import taxiApp.Exceptions.NotLoginException;
import taxiApp.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.springapp.repos.MessageRepository;
import taxiApp.springapp.services.CarsOwnerService;
import taxiApp.springapp.services.representations.DriverRepresentation;
import taxiApp.springapp.services.representations.MessageRepresentation;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/owner")
public class CarsOwnerController {

    private final CarsOwnerService ownerService;

    public CarsOwnerController(CarsOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/messages")
    @ResponseBody
    List<MessageRepresentation> getMessages(Principal principal) {
        checkPrincipal(principal);
        String login = principal.getName();
        CarsOwner owner = ownerService.getByLogin(login);
        return ownerService.getMessagesRepr(owner);
    }

    @GetMapping("/cars")
    @ResponseBody
    List<Car> getCars(Principal principal) {
        checkPrincipal(principal);
        String login = principal.getName();
        CarsOwner owner = ownerService.getByLogin(login);
        List<Car> cars = ownerService.getCars(owner);
        for (Car c : cars) {
            System.out.println(c);
        }
        return cars;
    }

    @GetMapping("/cvs")
    @ResponseBody
    List<CV> getCVs(Principal principal) {
        checkPrincipal(principal);
        String login = principal.getName();
        CarsOwner owner = ownerService.getByLogin(login);
        return ownerService.getCVs();
    }

    @GetMapping("/drivers")
    @ResponseBody
    List<DriverRepresentation> getDrivers(Principal principal) {
        checkPrincipal(principal);
        String login = principal.getName();
        CarsOwner owner = ownerService.getByLogin(login);
        return ownerService.getDrivers();
    }

    @GetMapping("/cv/{cvId}/nack")
    @ResponseBody
    void nackCV(Principal principal, @PathVariable Long cvId) throws NoEntityException {
        checkPrincipal(principal);
        String login = principal.getName();
        CarsOwner owner = ownerService.getByLogin(login);
        ownerService.nackCV(owner, cvId);
    }

    @GetMapping("/cv/{cvId}/ack/{carId}")
    @ResponseBody
    void ackCV(Principal principal, @PathVariable Long cvId, @PathVariable Long carId) throws CarIsUsingException, NoEntityException {
        checkPrincipal(principal);
        String login = principal.getName();
        CarsOwner owner = ownerService.getByLogin(login);
        ownerService.ackCV(owner, cvId, carId);
    }

    private void checkPrincipal(Principal principal) {
        if (null == principal) {
            throw new NotLoginException();
        }
    }
}
