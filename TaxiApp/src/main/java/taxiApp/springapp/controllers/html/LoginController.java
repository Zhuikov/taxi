package taxiApp.springapp.controllers.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import taxiApp.core.*;
import taxiApp.springapp.repos.CarsOwnerRepository;
import taxiApp.springapp.repos.DriverRepository;
import taxiApp.springapp.repos.ManagerRepository;
import taxiApp.springapp.repos.TaxiClientRepository;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/login")
    public String postLogin(Principal principal, HttpServletRequest request, Model model) {
        System.out.println("login::POST");
        String login = request.getParameter("login");
        System.out.println("Request: " +  login);

        Driver driver = driverRepository.findByLogin(login);
        if (driver != null) {
            if (!driver.isActive()) {
                model.addAttribute("driverActiveError", true);
                return "Login";
            }
            addPrincipal(login);
            return "redirect:driver/front";
        }
        Manager manager = managerRepository.findByLogin(login);
        if (manager != null) {
            addPrincipal(login);
            return "redirect:manager/front";
        }
        CarsOwner owner = ownerRepository.findByLogin(login);
        if (owner != null) {
            addPrincipal(login);
            return "redirect:owner/front";
        }
        TaxiClient client = clientRepository.findByLogin(login);
        if (client != null) {
            addPrincipal(login);
            return "redirect:client/front";
        }

        model.addAttribute("loginError", true);
        return "Login";
    }

    private void addPrincipal(String login) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(login, "-");
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
