package com.example.CasinoDiscord.Game;


import com.example.CasinoDiscord.domains.BetResult;
import com.example.CasinoDiscord.domains.RouletteBet;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomResultGenerator {

    public BetResult generateBetResult(){
      BetResult result = new BetResult();
        Random random = new Random();
        result.setNumber(random.nextInt(37));
        int num = result.getNumber();

        if (num == 0) {
          return result;
        }

        //size
        if (num > 16) {
          result.setSize(RouletteBet.BIG);
        }
        else {
          result.setSize(RouletteBet.SMALL);
        }

        //even
      if (num % 2 == 0){
        result.setEven(RouletteBet.EVEN);
      }
      else {
        result.setEven(RouletteBet.ODD);

      }

      int[] red = new int[]{1, 3, 5 , 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

      result.setColor(RouletteBet.BLACK);
      for (int i : red) {
        if (i == num) {
          result.setColor(RouletteBet.RED);
          break;
        }
      }
      System.out.println(result.toString());
      return result;

    }
}
