package com.example.CasinoDiscord.Roulette;


import com.example.CasinoDiscord.domains.BetResult;
import com.example.CasinoDiscord.domains.RouletteBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class PayRoulettePrizes {
    @Autowired
    JdbcTemplate template;


    public HashMap<String, Float> givePrizes(List<RouletteBet> bets, BetResult betResult) {

        HashMap<String, Float> winners = new HashMap<>();
        for (RouletteBet bet : bets) {
            int color = betResult.color;
            int even = betResult.even;
            int size = betResult.size;
            int num = betResult.number;
            float money = bet.getMoney();

            if (bet.getColor() != color && bet.getEven() != even && bet.getSize() != size && bet.getNumber() != num) continue;

            if (bet.getColor() == color) {
                template.update("UPDATE chanel_user SET money = money + ? WHERE user_id = ? AND chanel_id = ?", money * 2, bet.getId().getUser().getUserId(), bet.getRouletteBetsTable().getId());
                addPrizeToHashMap(winners, bet.getId().getUser().getUsername(), money * 2);
                System.out.println(money);
            }
            if (bet.getEven() == even) {
                template.update("UPDATE chanel_user SET money = money + ? WHERE user_id = ? AND chanel_id = ?", money * 2, bet.getId().getUser().getUserId(), bet.getRouletteBetsTable().getId());
                addPrizeToHashMap(winners, bet.getId().getUser().getUsername(), money * 2);

            }
            if (bet.getSize() == size) {
                template.update("UPDATE chanel_user SET money = money + ? WHERE user_id = ? AND chanel_id = ?", money * 2, bet.getId().getUser().getUserId(), bet.getRouletteBetsTable().getId());
                addPrizeToHashMap(winners, bet.getId().getUser().getUsername(), money * 2);

            }
            if (bet.getNumber() == num) {
                template.update("UPDATE chanel_user SET money = money + ? WHERE user_id = ? AND chanel_id = ?", money * 32, bet.getId().getUser().getUserId(), bet.getRouletteBetsTable().getId());
                addPrizeToHashMap(winners, bet.getId().getUser().getUsername(), money * 36);

            }



        }

        return winners;

    }
    private void addPrizeToHashMap(HashMap<String, Float> winners, String key, float value) {
        if (winners.containsKey(key)) {
            winners.put(key, winners.get(key) + value);
            return;
        }
        winners.put(key, value);

    }
}
