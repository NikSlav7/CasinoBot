package com.example.CasinoDiscord.RussianRoulette;


import com.example.CasinoDiscord.domains.Chanel;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RussianRouletteTableId implements Serializable{


    @OneToOne
    Chanel chanel;

    public RussianRouletteTableId(Chanel chanel) {
        this.chanel = chanel;
    }
    public RussianRouletteTableId(){}

    public Chanel getChanel() {
        return chanel;
    }

    public void setChanel(Chanel chanel) {
        this.chanel = chanel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RussianRouletteTableId that = (RussianRouletteTableId) o;
        return Objects.equals(chanel, that.chanel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chanel);
    }
}
