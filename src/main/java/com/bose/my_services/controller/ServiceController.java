package com.bose.my_services.controller;

import com.bose.my_services.model.MessageEntity;
import com.bose.my_services.service.MessagingServices;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class ServiceController {

    private final MessagingServices messagingServices;

    // 1. Endpoint to Save Data (CRUD)
    @PostMapping("/api/index")
    public String addNewMessages(
            @RequestParam(value = "name", defaultValue = "MOMO User") String name) {
        messagingServices.addNewMessage(name);
        return "redirect:/api/messages";
    }

    // 2. COMBINED Endpoint to Display Saved Messages AND Handle AI Content
    @GetMapping("/api/messages")
    public String getAllMessages(
            @RequestParam(value = "aiPrompt", required = false) String aiPrompt, // Handles AI form submission
            Model model) {

        // --- Part 1: Messaging Service Logic ---
        List<MessageEntity> messages = messagingServices.findAllMessages();
        model.addAttribute("savedMessages", messages);

        // --- Part 2: AI Generation Logic (Uses GEMINI_API_KEY) ---
        String aiResponseText = "";
        String apiKey = System.getenv("GEMINI_API_KEY");

        if (aiPrompt != null && !aiPrompt.trim().isEmpty()) {

            if (apiKey == null || apiKey.isEmpty()) {
                aiResponseText = "ERROR: GEMINI_API_KEY environment variable is not set. Please set your API key from Google AI Studio.";
            } else {
                try {
                    // 1. Instantiate the Client (API Key Method)
                    Client client = new Client();

                    // 2. Generate content using the nested 'models' service
                    GenerateContentResponse response = client.models.generateContent(
                            "gemini-2.5-flash", // Using the currently recommended model
                            aiPrompt,
                            null
                    );

                    // --- 3. Markdown to HTML Conversion ---
                    String markdownText = response.text();

                    // Initialize Parser and Renderer
                    Parser parser = Parser.builder().build();
                    HtmlRenderer renderer = HtmlRenderer.builder().build();

                    // Convert the Markdown text to HTML
                    Node document = parser.parse(markdownText);
                    aiResponseText = renderer.render(document);
                    // aiResponseText now contains HTML (e.g., <strong>3</strong>), ready for th:utext

                } catch (Exception e) {
                    aiResponseText = "Error generating content: " + e.getMessage();
                }
            }
        }

        // Add the converted HTML (or error message) to the model
        model.addAttribute("aiResponse", aiResponseText);
        return "savedMessages";
    }

    // 3. Endpoint to display the new edit form (CRUD)
    @GetMapping("/api/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        MessageEntity message = messagingServices.findById(id);
        model.addAttribute("messageToEdit", message);
        return "editForm";
    }

    // 4. Endpoint to handle the submission of the updated message (CRUD)
    @PatchMapping("/api/update")
    public String updateMessage(@RequestParam("id") Long id, @RequestParam("name") String name) {
        messagingServices.updateMessage(id, name);
        return "redirect:/api/messages";
    }

    // 5. Endpoint to handle deletion (CRUD)
    @DeleteMapping("/api/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {
        messagingServices.deleteMessage(id);
        return "redirect:/api/messages";
    }
}