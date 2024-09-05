package vn.tayjava;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class WelcomeController {

    private final RestTemplate restTemplate;

    public WelcomeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${message.welcome:Hello!}")
    private String message;

    @GetMapping("/welcome")
    public String welcome() {
        System.out.println("================> Authentication service");
        return message;
    }

    @CircuitBreaker(name = "accountServiceCircuitBreaker", fallbackMethod = "errorMessage")
    @GetMapping("/call-account-service")
    public String callAccountService() {
        String response = restTemplate.getForObject("http://localhost:4953/account/service-name", String.class);
        return "<h1>Hello from the " + response + "!</h1>";
    }

    /**
     * Fall back method
     * @param throwable
     * @return string
     */
    public String errorMessage(Throwable throwable) {
        return "Service unavailable, please try again!";
    }

}
