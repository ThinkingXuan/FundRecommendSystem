package com.njupt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tb_user")
public class User {

    String _id;
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}