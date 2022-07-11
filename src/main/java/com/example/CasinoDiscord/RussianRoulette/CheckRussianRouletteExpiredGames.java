package com.example.CasinoDiscord.RussianRoulette;


import com.example.CasinoDiscord.Spring.SpringApplicationContextProvider;
import com.example.CasinoDiscord.messageSender.MessageSender;
import com.example.CasinoDiscord.repositories.MessageChannelRepo;
import com.example.CasinoDiscord.repositories.RussianRouletteBetsRepo;
import com.example.CasinoDiscord.repositories.RussianRouletteTablesRepo;
import com.example.CasinoDiscord.rowMappers.RussianRouletteRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@EnableScheduling
public class CheckRussianRouletteExpiredGames {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private final RussianRouletteBetsRepo betsRepo;

    private final RussianRouletteTablesRepo tablesRepo;

    private final MessageChannelRepo messageChannelRepo;

    private final PayRussianRoulettePrices payRussianRoulettePrices;



    public CheckRussianRouletteExpiredGames(RussianRouletteBetsRepo betsRepo, RussianRouletteTablesRepo tablesRepo, MessageChannelRepo messageChannelRepo, PayRussianRoulettePrices payRussianRoulettePrices) {
        this.betsRepo = betsRepo;
        this.tablesRepo = tablesRepo;
        this.messageChannelRepo = messageChannelRepo;
        this.payRussianRoulettePrices = payRussianRoulettePrices;
    }


    @Scheduled(fixedRate = 1000)
    public void checkExpiredGames() throws InterruptedException {
        List<RussianRouletteBet> rouletteBets = jdbcTemplate.query("SELECT * FROM russian_roulette_table JOIN russian_roulette_bet ON " +
                "russian_roulette_table.chanel_chanel_id = russian_roulette_bet.russian_roulette_table_chanel_chanel_id" +
                " WHERE " + System.currentTimeMillis() + " - russian_roulette_table.time_when_created > 10000", SpringApplicationContextProvider.getApplicationContext().getBean(RussianRouletteRowMapper.class));
        Set<RussianRouletteTable> tableSet = new HashSet<>();
        rouletteBets.forEach(r ->  tableSet.add(r.getRussianRouletteBetId().russianRouletteTable));
        tablesRepo.deleteAll(tableSet);
        if (rouletteBets.size() <= 1) return;


        for (RussianRouletteTable russianRouletteTable : tableSet) {
            List<RussianRouletteBet> russianRouletteBets = rouletteBets.stream()
                    .filter(bet -> bet.getRussianRouletteBetId().getRussianRouletteTable() == russianRouletteTable).collect(Collectors.toList());
             MessageSender.russianRouletteGameEnding(messageChannelRepo.getMessageChannel(russianRouletteTable.getChannelId()), russianRouletteBets, new Random().nextInt(russianRouletteBets.size()));
             payRussianRoulettePrices.payPrizes(russianRouletteBets, russianRouletteTable.getTableBet());
        }


    }
}
