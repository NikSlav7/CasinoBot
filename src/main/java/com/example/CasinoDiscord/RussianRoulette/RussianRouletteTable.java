package com.example.CasinoDiscord.RussianRoulette;


import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class RussianRouletteTable {




    @EmbeddedId
    RussianRouletteTableId russianRouletteTableId;


    private String channelId;
    private float tableBet;

    private String creatorId;

    private long timeWhenCreated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "russianRouletteBetId.russianRouletteTable")
    List<RussianRouletteBet> betList;


    public RussianRouletteTable() {
    }

    public RussianRouletteTable(RussianRouletteTableId russianRouletteTableId,String channelId,  float tableBet, long timeWhenCreated ,String creatorId, List<RussianRouletteBet> betList) {
        this.channelId = channelId;
        this.timeWhenCreated = timeWhenCreated;
        this.russianRouletteTableId = russianRouletteTableId;
        this.tableBet = tableBet;
        this.creatorId = creatorId;
        this.betList = betList;
    }



    public List<RussianRouletteBet> getBetList() {
        return betList;
   }

    public void setBetList(List<RussianRouletteBet> betList) {
        this.betList = betList;
   }

    public RussianRouletteTableId getRussianRouletteTableId() {
        return russianRouletteTableId;
    }

    public void setRussianRouletteTableId(RussianRouletteTableId russianRouletteTableId) {
        this.russianRouletteTableId = russianRouletteTableId;
    }

    public float getTableBet() {
        return tableBet;
    }

    public void setTableBet(float tableBet) {
        this.tableBet = tableBet;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public long getTimeWhenCreated() {
        return timeWhenCreated;
    }

    public void setTimeWhenCreated(long timeWhenCreated) {
        this.timeWhenCreated = timeWhenCreated;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
