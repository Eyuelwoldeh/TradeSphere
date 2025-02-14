package com.tradeplatform.tradeanalytics.repository;

import com.tradeplatform.tradeanalytics.model.Message;
import com.tradeplatform.tradeanalytics.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findBySenderUidAndReceiverUidOrReceiverUidAndSenderUid(
            String senderUid, String receiverUid,
            String receiverUidAlt, String senderUidAlt
    );

    List<Message> findBySenderUidOrReceiverUid(String senderUid, String receiverUid);
}