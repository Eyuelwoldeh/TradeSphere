package com.tradeplatform.tradeanalytics.controller;

import com.tradeplatform.tradeanalytics.model.Message;
import com.tradeplatform.tradeanalytics.model.User;
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

    @GetMapping("/{senderUid}/{receiverUid}")
    public ResponseEntity<List<Message>> getMessagesBetweenUsers(
            @PathVariable String senderUid,
            @PathVariable String receiverUid
    ) {
        List<Message> messages = messageService.getMessagesBetweenUsers(senderUid, receiverUid);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/chat-users/{uid}")
    public ResponseEntity<List<User>> getChatUsers(@PathVariable String uid) {
        List<User> chatUsers = messageService.getChatUsers(uid);
        return ResponseEntity.ok(chatUsers);
    }

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message savedMessage = messageService.sendMessage(
                message.getSenderUid(),
                message.getReceiverUid(),
                message.getContent()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }
}