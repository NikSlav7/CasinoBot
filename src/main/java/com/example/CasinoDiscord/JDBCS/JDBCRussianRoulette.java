package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.RussianRoulette.RussianRouletteBet;
import com.example.CasinoDiscord.RussianRoulette.RussianRouletteBetId;
import com.example.CasinoDiscord.RussianRoulette.RussianRouletteTable;
import com.example.CasinoDiscord.RussianRoulette.RussianRouletteTableId;
import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.RouletteBet;
import com.example.CasinoDiscord.domains.RouletteBetsTable;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.RussianRouletteBetsRepo;
import com.example.CasinoDiscord.repositories.RussianRouletteTablesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
public class JDBCRussianRoulette {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RussianRouletteTablesRepo tablesRepo;

    @Autowired
    RussianRouletteBetsRepo betsRepo;

    @Autowired
    ChanelsRepo chanelsRepo;


    @Transactional
    public RussianRouletteTable startNewGame(RussianRouletteTableId id, String chanelId, String creatorId, float betAmount, long timeWhenCreated) {
        RussianRouletteTable betsTable = new RussianRouletteTable();
        betsTable.setRussianRouletteTableId(id);
        betsTable.setTableBet(betAmount);
        betsTable.setCreatorId(creatorId);
        betsTable.setTimeWhenCreated(timeWhenCreated);
        betsTable.setChannelId(chanelId);
        tablesRepo.save(betsTable);
        return betsTable;
    }

    @Transactional
    public RussianRouletteBet addBet(RussianRouletteBetId betId, String username) {
        RussianRouletteBet rouletteBet = new RussianRouletteBet();
        rouletteBet.setRussianRouletteBetId(betId);
        rouletteBet.setUsername(username);
        betsRepo.save(rouletteBet);
        return rouletteBet;
    }
}
