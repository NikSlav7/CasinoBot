package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.Person;
import com.example.CasinoDiscord.domains.UserChanelId;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.transaction.Transactional;
import java.util.List;

public interface PersonJDBCInterface {
    Person findByName(String name);
    List<Person> findAll();
    int addMoney(String userId, float amountOfMoney);

    public int payMoney(UserChanelId senderUserId, UserChanelId receiverUserId, float amountOfMoney, MessageReceivedEvent event);

}
