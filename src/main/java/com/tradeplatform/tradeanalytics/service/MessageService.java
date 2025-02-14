package com.tradeplatform.tradeanalytics.service;

import com.tradeplatform.tradeanalytics.model.Message;
import com.tradeplatform.tradeanalytics.model.User;
import com.tradeplatform.tradeanalytics.repository.MessageRepository;
import com.tradeplatform.tradeanalytics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;  // Add UserRepository to fetch user details

    public List<Message> getMessagesBetweenUsers(String senderUid, String receiverUid) {
        return messageRepository.findBySenderUidAndReceiverUidOrReceiverUidAndSenderUid(
                senderUid, receiverUid, receiverUid, senderUid
        );
    }

    public List<User> getChatUsers(String uid) {
        List<Message> messages = messageRepository.findBySenderUidOrReceiverUid(uid, uid);

        // Extract distinct UIDs of chat users
        List<String> chatUserIds = messages.stream()
                .map(m -> m.getSenderUid().equals(uid) ? m.getReceiverUid() : m.getSenderUid())
                .distinct()
                .collect(Collectors.toList());

        // Fetch full user details based on extracted UIDs
        return userRepository.findByUidIn(chatUserIds);
    }

    public Message sendMessage(String senderUid, String receiverUid, String content) {
        Message message = new Message();
        message.setSenderUid(senderUid);
        message.setReceiverUid(receiverUid);
        message.setContent(content);
        return messageRepository.save(message);
    }
}