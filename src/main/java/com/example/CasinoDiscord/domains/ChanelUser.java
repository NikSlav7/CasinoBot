package com.example.CasinoDiscord.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@Entity
@Table(name = "ChanelUser")
public class ChanelUser implements Serializable {

    @EmbeddedId
    UserChanelId userChanelId;

    String username;
    float money;


    public ChanelUser(UserChanelId id, String username, float money) {

        this.userChanelId = id;
        this.username = username;
        this.money = money;
    }


    public ChanelUser() {
    }

    public UserChanelId getId() {
        return userChanelId;
    }

    public void setId(UserChanelId id) {
        this.userChanelId = id;
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
