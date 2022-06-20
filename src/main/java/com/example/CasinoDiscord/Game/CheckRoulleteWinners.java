package com.example.CasinoDiscord.Game;


import com.example.CasinoDiscord.MessageEditors.MessageEditor;
import com.example.CasinoDiscord.Spring.SpringApplicationContextProvider;
import com.example.CasinoDiscord.domains.BetResult;
import com.example.CasinoDiscord.domains.RouletteBet;
import com.example.CasinoDiscord.messageSender.MessageSender;
import com.example.CasinoDiscord.repositories.MessageChannelRepo;
import com.example.CasinoDiscord.repositories.RouletteBetsTableRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import com.example.CasinoDiscord.rowMappers.RouletteBetsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@EnableScheduling
public class CheckRoulleteWinners {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RouletteBetsTableRepo betsTableRepo;


    @Autowired
    RandomResultGenerator randomResultGenerator;


    @Autowired
    PayRoulettePrizes payRoulettePrizes;

    @Autowired
    UsersRepo usersRepo;

    private final MessageChannelRepo repo;

    private final MessageEditor messageEditor;



    public CheckRoulleteWinners(MessageChannelRepo repo, MessageEditor messageEditor) {
        this.repo = repo;
        this.messageEditor = messageEditor;
    }



    @Scheduled(fixedRate = 10000)
    @Transactional
    public void checkExpiredGames(){

        List<RouletteBet> rouletteBets = jdbcTemplate.query("SELECT roulette.uuid, roulette.color, roulette.even, roulette.size, roulette.number ,roulette.user_user_id, roulette_bets_table.id, roulette.money  FROM roulette JOIN roulette_bets_table ON roulette.roulette_bets_table_id = roulette_bets_table.id WHERE " + System.currentTimeMillis() +" - time_when_created > 10000",
                SpringApplicationContextProvider.getApplicationContext().getBean(RouletteBetsRowMapper.class));
        betsTableRepo.deleteRouletteBetsTableByTimeWhenCreatedLessThan(System.currentTimeMillis() - 10000);
        System.out.println(rouletteBets.get(0).toString());

        BetResult betResult = randomResultGenerator.generateBetResult();
        if (rouletteBets.size() > 0) {
            MessageSender.rouletteGameResults(repo.getMessageChannel(rouletteBets.get(0).getRouletteBetsTable().getId()), payRoulettePrizes.givePrizes(rouletteBets, betResult), messageEditor.createResultString(betResult));

        }

    }




//    @Bean
//    public RowMapper getMapper() throws SQLException{
//        return new RowMapper() {
//            @Override
//            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//                RouletteId rouletteId = new RouletteId(usersRepo.findUserByUserId(rs.getString(6)).get(), rs.getString(1));
//                return new RouletteBet(rouletteId , rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(8), betsTableRepo.findRouletteBetsTableById(rs.getString(7)).get());
//            }
//        };
//    }
}
