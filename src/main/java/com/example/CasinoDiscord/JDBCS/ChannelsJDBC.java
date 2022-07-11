package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.Person;
import com.example.CasinoDiscord.domains.UserChanelId;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.List;

public interface ChannelsJDBC {

     void addMoney(String userId, String channelId, float amount);
     int checkIfChannelExists(String id);
     int checkIfChanelUserExists(Guild guild, String userId, String chanelId);


     void initializeNewChannelTable(String channelId, String userId,  Guild guild, MessageChannel messageChannel) throws InterruptedException;
     void pay(UserChanelId sender, UserChanelId receiver, float amount);

     void removeMoney(String userId, String channelId, float amount);

     boolean checkSufficientFunds(UserChanelId userChanelId, float amount);



}
