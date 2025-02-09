package com.tradeplatform.tradeanalytics.controller;

import com.tradeplatform.tradeanalytics.model.Message;
import com.tradeplatform.tradeanalytics.service.MessageService;
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

    @GetMapping("/{uid}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String uid) {
        List<Message> messages = messageService.getUserMessages(uid);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/send_message")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message savedMessage = messageService.sendMessage(
                message.getSenderUid(),
                message.getReceiverUid(),
                message.getContent()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }
}