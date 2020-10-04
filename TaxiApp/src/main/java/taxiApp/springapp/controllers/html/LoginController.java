package taxiApp.springapp.controllers.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping(value = {"/index", "/"})
    public String index() {
        return "redirect:login";
    }

    @RequestMapping("/login")
    public String login() {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");
        return "Login";
    }
}
