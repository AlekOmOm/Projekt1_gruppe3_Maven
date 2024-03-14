package dk.kea.projekt1_gruppe3_maven.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class CompanyController {
    @GetMapping("/company")
    public String bmiCalculator(){ return "Company";
    }
}
