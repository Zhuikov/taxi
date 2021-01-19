package taxiApp.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import taxiApp.core.CarsOwner;
import taxiApp.core.Driver;
import taxiApp.core.Manager;
import taxiApp.core.TaxiClient;
import taxiApp.springapp.repos.CarsOwnerRepository;
import taxiApp.springapp.repos.DriverRepository;
import taxiApp.springapp.repos.ManagerRepository;
import taxiApp.springapp.repos.TaxiClientRepository;
import taxiApp.springapp.services.representations.Login;
import taxiApp.springapp.services.representations.UserData;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.security.Principal;

@Controller
public class LoginController {

    private final DriverRepository driverRepository;
    private final CarsOwnerRepository ownerRepository;
    private final TaxiClientRepository clientRepository;
    private final ManagerRepository managerRepository;

    public LoginController(@Autowired DriverRepository driverRepository, @Autowired CarsOwnerRepository ownerRepository,
                           @Autowired TaxiClientRepository clientRepository, @Autowired ManagerRepository managerRepository) {
        this.driverRepository = driverRepository;
        this.ownerRepository = ownerRepository;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
    }

    @RequestMapping(value = {"/index", "/"})
    public String index() {
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("login::GET");
        return "Login";
    }


    @RequestMapping(value = {"/hello/react"})
    public String React() {
        return "/react/index";
    }

    @PostMapping("/login")
    @ResponseBody
    public UserData postLogin(Principal principal, HttpServletRequest request, @RequestBody Login body) {
        System.out.println("login::POST");
        System.out.println(body);
        String login = body.getLogin();
        System.out.println("Request: " + login);

        Driver driver = driverRepository.findByLogin(login);
        if (driver != null) {
            if (!driver.isActive()) {
                return new UserData("", "", "NOT_ACTIVE");
            }
            addPrincipal(login);
            return new UserData(driver.getPersonInfo().getName(), driver.getPersonInfo().getSurname(), "DRIVER");
        }
        Manager manager = managerRepository.findByLogin(login);
        if (manager != null) {
            addPrincipal(login);
            return new UserData(manager.getPersonInfo().getName(), manager.getPersonInfo().getSurname(), "MANAGER");
        }
        CarsOwner owner = ownerRepository.findByLogin(login);
        if (owner != null) {
            addPrincipal(login);
            return new UserData(owner.getPersonInfo().getName(), owner.getPersonInfo().getSurname(), "OWNER");
        }
        TaxiClient client = clientRepository.findByLogin(login);
        if (client != null) {
            addPrincipal(login);
            return new UserData(client.getPersonInfo().getName(), client.getPersonInfo().getSurname(), "CLIENT");
        }

        return new UserData();
    }

    private void addPrincipal(String login) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(login, "-");
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
