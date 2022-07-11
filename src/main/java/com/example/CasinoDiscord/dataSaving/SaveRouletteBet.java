package com.example.CasinoDiscord.dataSaving;


import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.RouletteBetsTable;
import com.example.CasinoDiscord.domains.RouletteId;
import com.example.CasinoDiscord.domains.User;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "saved_roulette_bet")

public class SaveRouletteBet {


    @Id
    String id;

    int color;

    int even;

    int size;

    int number = -1;

    float money;


    Date betCreationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    User betSender;

    @ManyToOne(fetch = FetchType.LAZY)
    Chanel betChanel;



    public SaveRouletteBet(String id, int color, int even, int size, int number, float money, Date betCreationTime, User betSender, Chanel betChanel) {
        this.id = id;
        this.color = color;
        this.even = even;
        this.size = size;
        this.number = number;
        this.money = money;
        this.betCreationTime = betCreationTime;
        this.betSender = betSender;
        this.betChanel = betChanel;
    }

    public SaveRouletteBet() {
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getEven() {
        return even;
    }

    public void setEven(int even) {
        this.even = even;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
