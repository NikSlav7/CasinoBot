package com.example.CasinoDiscord.domains;


import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class RouletteBetsTable {

    @Id
    String id;

    long timeWhenCreated;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "rouletteBetsTable")
    List<RouletteBet> rouletteBetList;


    public RouletteBetsTable(String id, List<RouletteBet> rouletteBetList) {
        this.id = id;
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
}
