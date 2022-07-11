package com.example.CasinoDiscord.dataSaving;

import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "saved_messages")
public class SaveMessage {
    @Id
    String uuid;

    @Column(columnDefinition = "TEXT")
    String messageText;

    @ManyToOne(fetch = FetchType.EAGER)
    User user;


    @ManyToOne(fetch = FetchType.EAGER)
    Chanel chanel;

    Date sendTime;

    public SaveMessage() {
    }

    public SaveMessage(String uuid, String messageText,  User user, Chanel chanel, Date sendTime) {
        this.messageText = messageText;
        this.uuid = uuid;
        this.user = user;
        this.chanel = chanel;
        this.sendTime = sendTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chanel getChanel() {
        return chanel;
    }

    public void setChanel(Chanel chanel) {
        this.chanel = chanel;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}

