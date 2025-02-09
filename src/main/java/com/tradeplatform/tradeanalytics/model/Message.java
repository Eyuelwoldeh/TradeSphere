package com.tradeplatform.tradeanalytics.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "messages")
@AllArgsConstructor
public class Message {

    @Id
    private ObjectId id;  // String-based ID instead of ObjectId
    private String senderUid;  // Use uid instead of ObjectId
    private String receiverUid;
    private String content;
    private LocalDateTime timestamp;

    public Message() {}

    public Message(String senderUid, String receiverUid, String content) {
        this.senderUid = senderUid;
        this.receiverUid = receiverUid;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid;
    }

    public String getReceiverUid() {
        return receiverUid;
    }

    public void setReceiverUid(String receiverUid) {
        this.receiverUid = receiverUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}