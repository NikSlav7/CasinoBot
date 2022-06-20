package com.example.CasinoDiscord.domains;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Entity(name = "roulette")
@Table(name = "Roulette")

public class RouletteBet {

    @Transient
    public static int RED = 1;
    @Transient
    public static int BLACK = 2;

    @Transient
    public static int EVEN  = 1;
    @Transient
    public static int ODD = 2;

    @Transient
    public static int SMALL = 1;

    @Transient
    public static int BIG = 2;

    @EmbeddedId
    RouletteId id;

    int color;

    int even;

    int size;

    int number = -1;

    float money;


    @ManyToOne(fetch = FetchType.LAZY)
    RouletteBetsTable rouletteBetsTable;


    public RouletteBet(RouletteId rouletteId, int color, int even, int size, int number, float money) {
        this.id = rouletteId;
        this.color = color;
        this.even = even;
        this.size = size;
        this.money = money;
    }
    public RouletteBet(RouletteId rouletteId, int color, int even, int size, int number, float money, RouletteBetsTable rouletteBetsTable) {
        this.id = rouletteId;
        this.color = color;
        this.even = even;
        this.size = size;
        this.money = money;
        this.rouletteBetsTable = rouletteBetsTable;
    }


    public RouletteBet() {
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void setId(RouletteId id){
        this.id = id;
    }

    public RouletteId getId() {
        return id;
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

    public RouletteBetsTable getRouletteBetsTable() {
        return rouletteBetsTable;
    }

    public void setRouletteBetsTable(RouletteBetsTable rouletteBetsTable) {
        this.rouletteBetsTable = rouletteBetsTable;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "RouletteBet{" +
                "id=" + id +
                ", color=" + color +
                ", even=" + even +
                ", size=" + size +
                ", number=" + number +
                ", money=" + money +
                ", rouletteBetsTable=" + rouletteBetsTable +
                '}';
    }
}
