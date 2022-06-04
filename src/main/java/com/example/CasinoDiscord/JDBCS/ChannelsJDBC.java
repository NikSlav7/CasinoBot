package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.Person;
import net.dv8tion.jda.api.entities.Guild;

import java.util.List;

public interface ChannelsJDBC {
     int addMoney(String id, float amount);
     int checkIfChannelExists(String id);
     void initializeNewChannelTable(String channelId, Guild guild);

}
