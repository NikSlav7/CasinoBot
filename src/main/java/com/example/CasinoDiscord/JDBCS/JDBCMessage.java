package com.example.CasinoDiscord.JDBCS;


import com.example.CasinoDiscord.Spring.SpringApplicationContextProvider;
import com.example.CasinoDiscord.dataSaving.SaveMessage;
import com.example.CasinoDiscord.messageSender.MessageSender;
import com.example.CasinoDiscord.rowMappers.SavedMessagesRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JDBCMessage {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<SaveMessage> getChanelUserMessages(String holderUserId, String holderChanelId, int limit) {
        return jdbcTemplate.query("SELECT * FROM saved_messages WHERE user_user_id = ? AND chanel_chanel_id = ? ORDER BY send_time desc LIMIT ?", SpringApplicationContextProvider.getApplicationContext().getBean(SavedMessagesRowMapper.class), holderUserId, holderChanelId, Math.min(limit, 50));
    }




}
