package sg.ntu.edu.simpleplayerstats;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// @Controller
// @ResponseBody

@RestController 
public class ResourceController {
    @GetMapping("/home")
    public String homeEndpoint() {
      return "home";
    }


    
}