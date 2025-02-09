package com.tradeplatform.tradeanalytics.controller;

import com.tradeplatform.tradeanalytics.model.Message;
import com.tradeplatform.tradeanalytics.service.MessageService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // Get all messages for a user
    @GetMapping("/{userId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String userId) {
        try {
            // Trim any potential whitespace
            userId = userId.trim();

            // Log the received userId for debugging
            System.out.println("Received userId: " + userId);

            // Ensure userId is a valid ObjectId
            if (!ObjectId.isValid(userId)) {
                System.out.println("Invalid ObjectId format for userId: " + userId);
                return ResponseEntity.badRequest().body(null); // 400 Bad Request if invalid
            }

            List<Message> messages = messageService.getUserMessages(new String(userId));
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            // Log the exception for debugging
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Send a message
    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message savedMessage = messageService.sendMessage(
                message.getSenderId(),
                message.getReceiverId(),
                message.getContent()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }
}