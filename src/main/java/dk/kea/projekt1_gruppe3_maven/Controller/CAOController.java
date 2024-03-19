package dk.kea.projekt1_gruppe3_maven.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller

public class CAOController {
    @GetMapping("/Company")
    public String Company(){ return "Company";
    }

    @GetMapping("/Ordering")
    public String Ordering(){ return "Ordering";
    }

    @GetMapping("/OrderConfirmed")
    public String OrderConfirmed(){ return "OrderConfirmed";}

    @GetMapping("/PrivateConsumer")
    public String PrivateConsumer(){return "PrivateConsumer";}



}

