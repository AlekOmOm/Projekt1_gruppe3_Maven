package dk.kea.projekt1_gruppe3_maven.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping ("/home")
    public String goHome() {
        return "home";
    }
}
