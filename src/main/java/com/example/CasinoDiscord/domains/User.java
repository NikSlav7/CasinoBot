package com.example.CasinoDiscord.domains;


import com.example.CasinoDiscord.dataSaving.SaveMessage;
import com.example.CasinoDiscord.dataSaving.SaveRouletteBet;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Table(name = "users")
public class User {


    @Id
    @Column(name = "user_id")
    String userId;

    String username;


    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "id.user", fetch = FetchType.LAZY)
    List<RouletteBet> rouletteBetList;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userChanelId.user", fetch = FetchType.EAGER)
    List<ChanelUser> chanelUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<SaveMessage> saveMessages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "betSender")
    List<SaveRouletteBet> saveRouletteBets;

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

    public List<RouletteBet> getRouletteBetList() {
        return rouletteBetList;
    }

    public void setRouletteBetList(List<RouletteBet> rouletteBetList) {
        this.rouletteBetList = rouletteBetList;
    }

    public List<ChanelUser> getChanelUser() {
        return chanelUser;
    }

    public void setChanelUser(List<ChanelUser> chanelUser) {
        this.chanelUser = chanelUser;
    }

    public List<SaveMessage> getSaveMessages() {
        return saveMessages;
    }

    public void setSaveMessages(List<SaveMessage> saveMessages) {
        this.saveMessages = saveMessages;
    }

    public List<SaveRouletteBet> getSaveRouletteBets() {
        return saveRouletteBets;
    }

    public void setSaveRouletteBets(List<SaveRouletteBet> saveRouletteBets) {
        this.saveRouletteBets = saveRouletteBets;
    }
}
