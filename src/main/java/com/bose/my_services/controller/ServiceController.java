package com.bose.my_services.controller;

import com.bose.my_services.service.MessagingServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.bose.my_services.model.MessageEntity;

@Controller // Use @Controller for server-side rendering
@RequiredArgsConstructor
public class ServiceController {

    private final MessagingServices messagingServices;

    // 1. Endpoint to Save Data and its now triggered from the form submission used in savedMessages.html
    @PostMapping("/api/index")
    public String addNewMessages(
            @RequestParam(value = "name", defaultValue = "MOMO User") String name
    ) {
        // Delegate all processing and database logic to the service.
        String result = messagingServices.addNewMessage(name);

        // After saving to H2 DB, redirect the user to the display page.
        return "redirect:/api/messages";
    }

    // 2. NEW Endpoint to Display Data in HTML Table
    @GetMapping("/api/messages")
    public String getAllMessages(Model model) { //Accept the Model object

        // Fetch the data list from the service
        List<MessageEntity> messages = messagingServices.findAllMessages();

        // Add the data to the Model object, naming the list 'messages'
        model.addAttribute("savedMessages", messages);

        // Return the name of the template file (savedMessages.html)
        return "savedMessages";
    }

    // 3. Endpoint to display the new edit form
    @GetMapping("/api/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        // Fetch the existing message entity by ID
        MessageEntity message = messagingServices.findById(id);

        // Add the entity to the model so the form can pre-populate the current data
        model.addAttribute("messageToEdit", message);

        // Return the name of the new template
        return "editForm";
    }

    // 4. Endpoint to handle the submission of the updated message
    @PatchMapping("/api/update")
    public String updateMessage(@RequestParam("id") Long id, @RequestParam("name") String name) {

        // Delegate the update logic to the service
        messagingServices.updateMessage(id, name);

        // Redirect back to the table to show the updated list
        return "redirect:/api/messages";
    }

    // 5. Endpoint to handle deletion (DELETE)
    // Using @GetMapping for easy hyperlinking in Thymeleaf.
    @DeleteMapping("/api/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {
        //messagingServices.deleteMessage(id);
        return "redirect:/api/messages";
    }
}