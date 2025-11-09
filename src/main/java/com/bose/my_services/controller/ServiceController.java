package com.bose.my_services.controller;

import com.bose.my_services.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ServiceController {

    // Inject the service layer
    private final GreetingService greetingService;

    @GetMapping("/api/index")
    // The method now correctly expects a String return type.
    public String getGreeting(@RequestParam(value = "name", defaultValue = "MOMO User") String name) {
        // Delegate all processing and database logic to the service.
        return greetingService.generateGreeting(name);
    }
}