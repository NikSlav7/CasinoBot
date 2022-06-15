package com.example.CasinoDiscord.Game;


import com.example.CasinoDiscord.domains.RouletteBet;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RouletteBetDeterminator {

    public RouletteBet determineRouletteBet(String bet){
        RouletteBet rouletteBet = new RouletteBet();

        switch (bet.toLowerCase(Locale.ROOT)) {
            case "red":
                rouletteBet.setColor(RouletteBet.RED);
                break;
            case "black":
                rouletteBet.setColor(RouletteBet.BLACK);
                break;
            case "even":
                rouletteBet.setEven(RouletteBet.EVEN);
                break;
            case "odd":
                rouletteBet.setEven(RouletteBet.ODD);
                break;
            case "1-18":
                rouletteBet.setSize(RouletteBet.SMALL);
                break;
            case "19-36":
                rouletteBet.setSize(RouletteBet.BIG);
                break;
        }
        try {
            int i = Integer.parseInt(bet);
            if (i < 36 && i > 0) {
                rouletteBet.setNumber(i);
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        System.out.println("Loh");
        return rouletteBet;
    }
}
