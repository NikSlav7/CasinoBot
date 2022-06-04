package com.example.CasinoDiscord.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@Entity
@Table(name = "ChanelUser")
public class ChanelUser implements Serializable {

    @EmbeddedId
    UserId userId;

    String username;
    float money;


    public ChanelUser(UserId id, String username, float money) {

        this.userId = id;
        this.username = username;
        this.money = money;
    }

    public ChanelUser() {
    }

    public UserId getId() {
        return userId;
    }

    public void setId(UserId id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }



}
