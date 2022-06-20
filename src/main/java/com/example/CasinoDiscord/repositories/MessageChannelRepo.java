package com.example.CasinoDiscord.repositories;

import net.dv8tion.jda.api.entities.MessageChannel;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MessageChannelRepo {
    private final HashMap<String, MessageChannel> map;

    public MessageChannelRepo(){
        this.map = new HashMap<>();
    }

    public void addMessageChannel(String chanelId, MessageChannel messageChannel){
        map.put(chanelId, messageChannel);
    }

    public MessageChannel getMessageChannel(String id) {
        return map.get(id);
    }
}
