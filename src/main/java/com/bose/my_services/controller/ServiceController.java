package com.bose.my_services.controller;

import com.bose.my_services.model.Greeting;
import com.bose.my_services.service.GreetingService; // Import the new Service for dependency injection
import lombok.RequiredArgsConstructor; // Lombok annotation for constructor generation
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // Lombok will create a constructor for final fields

// This class is a REST controller that handles HTTP requests
public class ServiceController {

    // Dependency Injection: Spring will automatically provide an instance
    // of GreetingService when the controller is created.
    private final GreetingService greetingService;

    @GetMapping("/api/index")
    public Greeting getGreeting(
            @RequestParam(value = "name", defaultValue = "MOMO ") String name
    ) {
        // Delegation: The Controller delegates the actual business work to the service package
        return greetingService.generateGreeting(name);
        //return String.format("Hello, %s! Thanks for connecting to the business service.", name);
    }
}