package com.tradeplatform.tradeanalytics.service;

import com.tradeplatform.tradeanalytics.model.Message;
import com.tradeplatform.tradeanalytics.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getUserMessages(String uid) {
        return messageRepository.findBySenderUidOrReceiverUid(uid, uid);
    }

    public Message sendMessage(String senderUid, String receiverUid, String content) {
        Message message = new Message();
        message.setSenderUid(senderUid);
        message.setReceiverUid(receiverUid);
        message.setContent(content);
        return messageRepository.save(message);
    }
}