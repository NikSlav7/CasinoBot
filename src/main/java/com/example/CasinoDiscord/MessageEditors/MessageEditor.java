package com.example.CasinoDiscord.MessageEditors;


import com.example.CasinoDiscord.domains.BetResult;
import com.example.CasinoDiscord.domains.RouletteBet;
import org.springframework.stereotype.Component;

@Component
public class MessageEditor {


    public String createClearNameId(String id){
        return id.replace("<@", "").replace("<@","").replace(">", "");
    }

    public String createResultString(BetResult bet) {
        String color = "BLACK";
        int[] red = new int[]{1, 3, 5 , 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        if (bet.getNumber() == 0) {
            return "Roulette result is " + bet.getNumber();
        }
        for (int i : red) {
            if (i == bet.getColor()) {
                color = "RED";
            }
        }
        return "Roulette result is " + bet.getNumber() + ", " + color;
    }
}
