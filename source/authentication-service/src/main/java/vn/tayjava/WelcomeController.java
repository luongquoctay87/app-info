package vn.tayjava;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class WelcomeController {

    private RemoteService remoteService;

    @Value("${message.welcome:Hello!}")
    private String message;

    public WelcomeController(RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        System.out.println("================> Authentication service");
        return message;
    }

    @GetMapping("/call-account-service")
    public String callAccountService() {
        return remoteService.callAccountService();
    }

}
