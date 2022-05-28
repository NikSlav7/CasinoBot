package com.example.CasinoDiscord.domains;

import javax.persistence.*;
import java.util.List;



@Entity
@Table(name = "ChanelUser")
public class ChanelUser {


    @Id
    @Column(name = "user_id", updatable = false, nullable = false)
    String id;

    String username;

    float money;

    @ManyToOne()
    @JoinColumn(name = "channel_id")
    Chanel chanel;

    public ChanelUser(String id, String username, float money) {
        this.id = id;
        this.username = username;
        this.money = money;
    }

    public ChanelUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Chanel getChanel() {
        return chanel;
    }

    public void setChanel(Chanel chanel) {
        this.chanel = chanel;
    }
}
