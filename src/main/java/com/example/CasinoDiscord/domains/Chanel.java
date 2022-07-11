package com.example.CasinoDiscord.domains;

import com.example.CasinoDiscord.RussianRoulette.RussianRouletteTable;
import com.example.CasinoDiscord.dataSaving.SaveMessage;
import com.example.CasinoDiscord.dataSaving.SaveRouletteBet;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "chanel")
@Table(name = "channels")
public class Chanel implements Serializable {


    @Id
    @Column(name = "chanel_id", updatable = false, nullable = false)
    String channelId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userChanelId.chanel", fetch = FetchType.EAGER)
    List<ChanelUser> chanelUsers;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chanel")
    List<SaveMessage> saveMessages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "betChanel")
    List<SaveRouletteBet> saveRouletteBets;


//    @OneToOne(mappedBy = "russianRouletteTableId.chanel")
//    RussianRouletteTable russianRouletteTable;

    public Chanel(String channelId, List<ChanelUser> channelUser) {
        this.channelId = channelId;
        chanelUsers = channelUser;
    }

    public Chanel(String channelId) {
        this.channelId = channelId;
    }

    public Chanel() {
        chanelUsers  = new ArrayList<ChanelUser>();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public List<ChanelUser> getChannelUser() {
        return chanelUsers;
    }

    public void setChannelUser(List<ChanelUser> channelUser) {
        chanelUsers = channelUser;
    }

    public List<ChanelUser> getChanelUsers() {
        return chanelUsers;
    }

    public void setChanelUsers(List<ChanelUser> chanelUsers) {
        this.chanelUsers = chanelUsers;
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

//    public RussianRouletteTable getRussianRouletteTable() {
//        return russianRouletteTable;
//    }
//
//    public void setRussianRouletteTable(RussianRouletteTable russianRouletteTable) {
//        this.russianRouletteTable = russianRouletteTable;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chanel chanel = (Chanel) o;
        return Objects.equals(channelId, chanel.channelId) && Objects.equals(chanelUsers, chanel.chanelUsers) && Objects.equals(saveMessages, chanel.saveMessages) && Objects.equals(saveRouletteBets, chanel.saveRouletteBets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelId, chanelUsers, saveMessages, saveRouletteBets);
    }
}
