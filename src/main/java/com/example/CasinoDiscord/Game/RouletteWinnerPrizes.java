package com.example.CasinoDiscord.Game;


import com.example.CasinoDiscord.domains.BetResult;
import com.example.CasinoDiscord.domains.RouletteBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouletteWinnerPrizes {
    @Autowired
    JdbcTemplate template;


    public void givePrizes(List<RouletteBet> bets, BetResult betResult) {

        for (RouletteBet bet : bets) {
            int color = betResult.color;
            int even = betResult.even;
            int size = betResult.size;
            int num = betResult.number;
            float money = bet.getMoney() * 2;

            if (bet.getColor() != color && bet.getEven() != even && bet.getSize() != size && bet.getNumber() != num) continue;

            if (bet.getColor() == color) {
                template.update("UPDATE chanel_user SET money = money + ? WHERE user_id = ? AND chanel_id = ?", money, bet.getId().getUser().getUserId(), bet.getRouletteBetsTable().getId());
                System.out.println("updating money");
            }
            System.out.println("determined winner" + betResult.color + " " + color + " " + money);



        }

    }
}
