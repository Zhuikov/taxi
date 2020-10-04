package springapp;

import Exceptions.NoEntityException;
import core.CV;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.CVRepository;

@RestController
public class TaxiController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/testGet/{id}")
    public CV getCV(@PathVariable int id) {
        CVRepository repo = CVRepository.getInstance();
        try {
            return repo.getById(id);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
