package com.example.CasinoDiscord.domains;


import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Table(name = "users")
public class User {


    @Id
    @Column(name = "user_id")
    String userId;


    String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId.chanel")
    List<ChanelUser> chanelUser;

    public User() {
    }

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
