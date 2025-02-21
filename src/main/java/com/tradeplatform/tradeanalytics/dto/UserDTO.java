package com.tradeplatform.tradeanalytics.dto;


import lombok.Data;

// I am using this class because I dont want to pass sensitive info to the reposnse when I load the user profile..
@Data
public class UserDTO {
    private String id;
    private String email;
    private String name;

    public UserDTO(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    // Getters
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
}
