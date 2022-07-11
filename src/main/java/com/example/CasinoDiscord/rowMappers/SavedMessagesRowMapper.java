package com.example.CasinoDiscord.rowMappers;

import com.example.CasinoDiscord.dataSaving.SaveMessage;
import com.example.CasinoDiscord.domains.RouletteBet;
import com.example.CasinoDiscord.domains.RouletteId;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.RouletteBetsTableRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class SavedMessagesRowMapper implements RowMapper<SaveMessage> {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    ChanelsRepo chanelsRepo;


    @Override
    public SaveMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
        SaveMessage saveMessage = new SaveMessage();
        String userId = rs.getString("user_user_id");
        String chanelId = rs.getString("chanel_chanel_id");
        saveMessage.setUser(usersRepo.findUserByUserId(userId).get());
        saveMessage.setChanel(chanelsRepo.findChanelByChannelId(chanelId).get());
        saveMessage.setUuid(rs.getString("uuid"));
        saveMessage.setSendTime(rs.getDate("send_time"));
        saveMessage.setMessageText(rs.getString("message_text"));
        return saveMessage;
    }
}
