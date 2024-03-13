package com.example.projekt1_gruppe3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    // This is the error page
    // This is the name of the html file to be returned
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
