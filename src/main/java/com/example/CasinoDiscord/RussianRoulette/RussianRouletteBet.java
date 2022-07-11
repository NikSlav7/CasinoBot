package com.example.CasinoDiscord.RussianRoulette;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class RussianRouletteBet {

    @EmbeddedId
    private RussianRouletteBetId russianRouletteBetId;

    private String username;

    public  RussianRouletteBet(){}

    public RussianRouletteBet(RussianRouletteBetId russianRouletteBetId, String username) {
        this.russianRouletteBetId = russianRouletteBetId;
        this.username = username;
    }

    public RussianRouletteBetId getRussianRouletteBetId() {
        return russianRouletteBetId;
    }

    public void setRussianRouletteBetId(RussianRouletteBetId russianRouletteBetId) {
        this.russianRouletteBetId = russianRouletteBetId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
