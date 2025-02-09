package com.tradeplatform.tradeanalytics.service;

import com.tradeplatform.tradeanalytics.model.Message;
import com.tradeplatform.tradeanalytics.repository.MessageRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getUserMessages(String userId) {
        return messageRepository.findBySenderIdOrReceiverId(userId, userId);
    }

    public Message sendMessage(String senderId, String receiverId, String content) {
        Message message = new Message(senderId, receiverId, content);
        return messageRepository.save(message);
    }
}