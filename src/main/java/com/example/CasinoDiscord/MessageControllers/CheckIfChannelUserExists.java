package com.example.CasinoDiscord.MessageControllers;

import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CheckIfChannelUserExists extends ListenerAdapter {


    private final ChanelsRepo chanelsRepo;
    private final JDBCChannels jdbcChannels;

    public CheckIfChannelUserExists(ChanelsRepo chanelsRepo, JDBCChannels jdbcChannels) {
        this.chanelsRepo = chanelsRepo;
        this.jdbcChannels = jdbcChannels;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String id = event.getAuthor().getId() + event.getChannel().getId();
//        jdbcChannels.checkIfChannelUserExists(id, event.getAuthor().getName(), chanelsRepo.getById(event.getChannel().getId()));

    }

}
