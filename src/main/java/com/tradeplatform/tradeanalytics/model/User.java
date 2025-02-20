package com.tradeplatform.tradeanalytics.model;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;

@Document(collection= "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private ObjectId id;


    private String uid;
    private String name;
    private String email;
    private String passwordHash;
    private String role;  // "importer", "exporter", "admin"
    private String company;
    private String location;
    private List<String> tradeInterests;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getTradeInterests() {
        return tradeInterests;
    }

    public void setTradeInterests(List<String> tradeInterests) {
        this.tradeInterests = tradeInterests;
    }
}
