package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.Person;

import java.util.List;

public interface ChannelsJDBC {
     int addMoney(String id, float amount);
     int checkIfChannelExists(String id);

}
