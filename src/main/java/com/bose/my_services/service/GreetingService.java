package com.bose.my_services.service;
import com.bose.my_services.model.Greeting;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;

@Service
// This tag tells Spring that this class is a business component performing service tasks like
// generating greetings message with unique IDs and formatting.
// And this class used by the Controller to delegate business logic actions.
public class GreetingService {

    private final AtomicLong counter = new AtomicLong(); // ID generator is now here
    /**
     * Generates and returns a new Greeting object.
     * @param name The name to use in the greeting content.
     * @return The final Greeting record.
     */
    public Greeting generateGreeting(String name) {
        long newId = counter.incrementAndGet();

        // The business logic for formatting the message is here
        String content = String.format("Hello, %s! Thanks for connecting to the business service.", name);
        return new Greeting(newId, content);
    }
}