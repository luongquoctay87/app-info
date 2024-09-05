package vn.tayjava;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteService {

    private final RestTemplate restTemplate;

    public RemoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "accountServiceCircuitBreaker", fallbackMethod = "errorMessage")
    public String callAccountService() {
        String response = restTemplate.getForObject("http://localhost:4953/account/service-name", String.class);
        return "<h1>Hello from the " + response + "!</h1>";
    }

    public String errorMessage(Throwable throwable) {
        return "Service unavailable, please try again";
    }
}
