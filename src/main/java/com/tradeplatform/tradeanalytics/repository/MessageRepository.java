package com.tradeplatform.tradeanalytics.repository;

import com.tradeplatform.tradeanalytics.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findBySenderUidOrReceiverUid(String senderUid, String receiverUid);
}