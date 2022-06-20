package com.example.CasinoDiscord.rowMappers;

import com.example.CasinoDiscord.domains.RouletteBet;
import com.example.CasinoDiscord.domains.RouletteBetsTable;
import com.example.CasinoDiscord.domains.RouletteId;
import com.example.CasinoDiscord.repositories.RouletteBetsTableRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class RouletteBetsRowMapper implements RowMapper<RouletteBet> {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    RouletteBetsTableRepo betsTableRepo;
    @Override
    public RouletteBet mapRow(ResultSet rs, int rowNum) throws SQLException {
        RouletteId rouletteId = new RouletteId(usersRepo.findUserByUserId(rs.getString(6)).get(), rs.getString(1));
        RouletteBet bet = new RouletteBet(rouletteId , rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(8), betsTableRepo.findRouletteBetsTableById(rs.getString(7)).get());
        System.out.println("Bet id eqials null " + rouletteId.getUuid());
        return bet;
    }
}
