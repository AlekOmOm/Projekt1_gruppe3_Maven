package com.example.projekt1_gruppe3.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class CompanyController {
    @GetMapping("/company")
    public String bmiCalculator(){ return "Company";
    }
}
