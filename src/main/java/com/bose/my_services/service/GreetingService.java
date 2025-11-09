package com.bose.my_services.service;

import com.bose.my_services.model.MessageEntity;
import com.bose.my_services.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreetingService {

    // Dependency Injection: Spring automatically provides the implementation of this interface
    private final MessageRepository messageRepository;

    /**
     * Generates a greeting message and persists it to the database.
     * @param name The name to use in the greeting.
     * @return The final string message.
     */
    public String generateGreeting(String name) {

        // 1. Business Logic: Create the message content
        String content = String.format("Hello, %s! Thanks for connecting. This message is being saved now in database.", name);

        // 2. Persistence Logic: Map the content to the Entity and save it
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setContent(content);
        messageRepository.save(messageEntity); // Saves the entity (creates a row in the DB table)

        // 3. Return the desired output
        return content;
    }
}