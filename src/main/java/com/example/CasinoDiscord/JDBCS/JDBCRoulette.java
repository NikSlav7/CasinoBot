package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.domains.RouletteBet;
import com.example.CasinoDiscord.domains.RouletteBetsTable;
import com.example.CasinoDiscord.repositories.RouletteBetsRepo;
import com.example.CasinoDiscord.repositories.RouletteBetsTableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;


@Component
public class JDBCRoulette {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RouletteBetsTableRepo rouletteBetsTableRepo;

    @Autowired
    RouletteBetsRepo rouletteBetsRepo;




    @Transactional
    public void startTheGame(String channelId, String messageChannelId){
        System.out.println(jdbcTemplate.update("INSERT INTO roulette_bets_table(id, chanel_id , time_when_created) VALUES (?, ?, ?) ON CONFLICT (id) DO NOTHING"
                , channelId, messageChannelId, System.currentTimeMillis()));




    }
    @Transactional
    public void setNewBet(RouletteBet rouletteBet, String channelId, String userId){
        Optional<RouletteBetsTable> betsTableOptional = rouletteBetsTableRepo.findRouletteBetsTableById(channelId);
        if (betsTableOptional.isEmpty()) throw new RuntimeException();
        RouletteBetsTable betsTable = betsTableOptional.get();
        betsTable.getRouletteResultsList().add(rouletteBet);
        rouletteBet.setRouletteBetsTable(betsTable);
    }





}
