package com.example.CasinoDiscord.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@Entity
@Table(name = "ChanelUser")
public class ChanelUser implements Serializable {

    @EmbeddedId
    UserChanelId userChanelId;

    String username;
    float money;

    int coinFlipWinChance;


    public ChanelUser(UserChanelId id, String username, float money) {

        this.userChanelId = id;
        this.username = username;
        this.money = money;
        coinFlipWinChance = 50;
    }



    public ChanelUser() {
    }

    public UserChanelId getId() {
        return userChanelId;
    }

    public void setId(UserChanelId id) {
        this.userChanelId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public UserChanelId getUserChanelId() {
        return userChanelId;
    }

    public void setUserChanelId(UserChanelId userChanelId) {
        this.userChanelId = userChanelId;
    }

    public int getCoinFlipWinChance() {
        return coinFlipWinChance;
    }

    public void setCoinFlipWinChance(int coinFlipWinChance) {
        this.coinFlipWinChance = coinFlipWinChance;
    }
    public void setCoinFlipWinChanceToDefault() {
        coinFlipWinChance = 50;
    }
    public void upgradeCoinFlipWinChance() {
        coinFlipWinChance = coinFlipWinChance == 75 ? coinFlipWinChance : coinFlipWinChance + 1;
    }
}
