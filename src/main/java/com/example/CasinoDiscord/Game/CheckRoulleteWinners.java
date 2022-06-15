package com.example.CasinoDiscord.Game;


import com.example.CasinoDiscord.Spring.SpringApplicationContextProvider;
import com.example.CasinoDiscord.domains.RouletteBet;
import com.example.CasinoDiscord.domains.RouletteId;
import com.example.CasinoDiscord.domains.User;
import com.example.CasinoDiscord.repositories.RouletteBetsTableRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import com.example.CasinoDiscord.rowMappers.RouletteBetsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
    RouletteWinnerPrizes rouletteWinnerPrizes;

    @Autowired
    UsersRepo usersRepo;




    @Scheduled(fixedRate = 10000)
    @Transactional
    public  void checkExpiredGames(){

        List<RouletteBet> rouletteBets = jdbcTemplate.query("SELECT roulette.uuid, roulette.color, roulette.even, roulette.size, roulette.number ,roulette.user_user_id, roulette_bets_table.id, roulette.money  FROM roulette JOIN roulette_bets_table ON roulette.roulette_bets_table_id = roulette_bets_table.id WHERE " + System.currentTimeMillis() +" - time_when_created > 1000",
                SpringApplicationContextProvider.getApplicationContext().getBean(RouletteBetsRowMapper.class));
        betsTableRepo.deleteRouletteBetsTableByTimeWhenCreatedLessThan(System.currentTimeMillis() - 10000);
        System.out.println(rouletteBets.get(0).toString());

        if (rouletteBets.size() > 0) {
            rouletteWinnerPrizes.givePrizes(rouletteBets, randomResultGenerator.generateBetResult());
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
