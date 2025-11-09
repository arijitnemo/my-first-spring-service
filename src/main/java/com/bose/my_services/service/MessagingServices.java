package com.bose.my_services.service;

import com.bose.my_services.model.MessageEntity;
import com.bose.my_services.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagingServices {

    // Dependency Injection: Spring automatically provides the implementation of this interface
    private final MessageRepository messageRepository;

    /**
     * Generates a greeting message and persists it to the database.
     * @param name The name to use in the greeting.
     * @return The final string message.
     */
    public String addNewMessage(String name) {

        // 1. Business Logic: Create the message content
        String content = String.format("Hello, %s ! Thanks for connecting. Your entry has been added.", name);

        // 2. Persistence Logic: Map the content to the Entity and save it
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setContent(content);
        messageRepository.save(messageEntity); // Saves the entity (creates a row in the DB table)

        // 3. Return the desired output
        return content;
    }

    /**
     * Fetches all saved messages from the database.
     * @return A List of all MessageEntity objects.
     */
    public List<MessageEntity> findAllMessages() {
        // Spring Data JPA provides findAll() automatically.
        return messageRepository.findAll();
    }

    // Find a single entity by ID
    public MessageEntity findById(Long id) {
        // Repository returns an Optional, so we get the value or throw an error (for simplicity)
        return messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + id));
    }

    // Update the message content
    public void updateMessage(Long id, String newName) {
        // 1. Find the existing entity
        MessageEntity message = findById(id);

        // 2. Create the new content based on the user's input
        String newContent = String.format("Hello, %s ! Thanks for connecting. Your entry has been updated.", newName);

        // 3. Update the entity object
        message.setContent(newContent);

        // 4. Save the entity (JPA uses the existing ID to perform an UPDATE instead of an INSERT)
        messageRepository.save(message);
    }

    // DELETE: Deletes a message by its ID
    public void deleteMessage(Long id) {
        // Spring Data JPA's JpaRepository provides deleteById(id) automatically
        messageRepository.deleteById(id);
    }
}