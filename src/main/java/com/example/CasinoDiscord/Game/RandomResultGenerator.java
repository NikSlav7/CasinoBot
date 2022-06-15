package com.example.CasinoDiscord.Game;


import com.example.CasinoDiscord.domains.BetResult;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomResultGenerator {

    public BetResult generateBetResult(){
      BetResult result = new BetResult();
        Random r1 = new Random();
        Random r2 = new Random();
        Random r3 = new Random();
        Random r4 = new Random();

        result.setColor(r1.nextInt(2) + 1);
        result.setEven(r2.nextInt(2) + 1);
        result.setSize(r3.nextInt(2) + 1);
        result.setNumber(r4.nextInt(37));
        return result;

    }
}
