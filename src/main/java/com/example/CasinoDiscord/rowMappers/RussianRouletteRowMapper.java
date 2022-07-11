package com.example.CasinoDiscord.rowMappers;

import com.example.CasinoDiscord.RussianRoulette.RussianRouletteBet;
import com.example.CasinoDiscord.RussianRoulette.RussianRouletteBetId;
import com.example.CasinoDiscord.RussianRoulette.RussianRouletteTable;
import com.example.CasinoDiscord.RussianRoulette.RussianRouletteTableId;
import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.User;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.RussianRouletteTablesRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class RussianRouletteRowMapper implements RowMapper<RussianRouletteBet> {

    private final UsersRepo usersRepo;
    private final ChanelsRepo chanelsRepo;
    private final RussianRouletteTablesRepo tablesRepo;


    @Autowired
    public RussianRouletteRowMapper(UsersRepo usersRepo, ChanelsRepo chanelsRepo, RussianRouletteTablesRepo tablesRepo) {
        this.usersRepo = usersRepo;
        this.chanelsRepo = chanelsRepo;
        this.tablesRepo = tablesRepo;
    }

    @Override
    public RussianRouletteBet mapRow(ResultSet rs, int rowNum) throws SQLException {
        RussianRouletteBet russianRouletteBet = new RussianRouletteBet();
        russianRouletteBet.setUsername(rs.getString("username"));
        User user = usersRepo.findUserByUserId(rs.getString("user_user_id")).get();
        Chanel chanel = chanelsRepo.findChanelByChannelId(rs.getString("russian_roulette_table_chanel_chanel_id")).get();
        RussianRouletteBetId russianRouletteBetId = new RussianRouletteBetId(tablesRepo.findRussianRouletteTableByRussianRouletteTableId(new RussianRouletteTableId(chanel)).get(), user);
        russianRouletteBet.setRussianRouletteBetId(russianRouletteBetId);
        return russianRouletteBet;
    }
}
