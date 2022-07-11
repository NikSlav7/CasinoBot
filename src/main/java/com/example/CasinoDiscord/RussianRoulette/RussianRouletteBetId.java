package com.example.CasinoDiscord.RussianRoulette;


import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.User;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
;import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RussianRouletteBetId implements Serializable {


    @ManyToOne(fetch = FetchType.LAZY)
    RussianRouletteTable russianRouletteTable;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    public RussianRouletteBetId() {
    }

    public RussianRouletteBetId(RussianRouletteTable russianRouletteTable, User user) {
        this.russianRouletteTable = russianRouletteTable;
        this.user = user;
    }

    public RussianRouletteTable getRussianRouletteTable() {
        return russianRouletteTable;
    }

    public void setRussianRouletteTable(RussianRouletteTable russianRouletteTable, User user) {
        this.russianRouletteTable = russianRouletteTable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RussianRouletteBetId that = (RussianRouletteBetId) o;
        return Objects.equals(russianRouletteTable, that.russianRouletteTable) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(russianRouletteTable, user);
    }
}
