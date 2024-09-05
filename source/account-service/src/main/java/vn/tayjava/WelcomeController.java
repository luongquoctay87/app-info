package vn.tayjava;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class WelcomeController {

    @Value("${message.welcome:Hello!}")
    private String message;

    @GetMapping("/welcome")
    public String welcome() {
        System.out.println("================> Account service");
        return message;
    }

    @GetMapping("/service-name")
    public String name() {
        return "ACCOUNT-SERVICE";
    }
}
