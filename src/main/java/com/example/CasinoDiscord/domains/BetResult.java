package com.example.CasinoDiscord.domains;

public class BetResult {
    public int color;

    public int even;

    public int size;

    public int number;

    public BetResult(int color, int even, int size, int number) {
        this.color = color;
        this.even = even;
        this.size = size;
        this.number = number;
    }

    public BetResult() {
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
