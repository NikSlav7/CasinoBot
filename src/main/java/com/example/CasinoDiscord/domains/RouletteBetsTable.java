package com.example.CasinoDiscord.domains;


import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class RouletteBetsTable {

    @Id
    String id;

    String chanelId;

    long timeWhenCreated;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "rouletteBetsTable")
    List<RouletteBet> rouletteBetList;


    public RouletteBetsTable(String id, String chanelId, List<RouletteBet> rouletteBetList) {
        this.id = id;
        this.chanelId = chanelId;
        this.rouletteBetList = rouletteBetList;
    }

    public RouletteBetsTable() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<RouletteBet> getRouletteResultsList() {
        return rouletteBetList;
    }

    public void setRouletteResultsList(List<RouletteBet> rouletteBetList) {
        this.rouletteBetList = rouletteBetList;
    }

    public long getTimeWhenCreated() {
        return timeWhenCreated;
    }

    public void setTimeWhenCreated(long timeWhenCreated) {
        this.timeWhenCreated = timeWhenCreated;
    }

    public String getChanelId() {
        return chanelId;
    }

    public void setChanelId(String chanelId) {
        this.chanelId = chanelId;
    }
}
