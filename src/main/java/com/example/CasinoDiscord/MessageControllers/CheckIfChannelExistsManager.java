package com.example.CasinoDiscord.MessageControllers;

import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import com.example.CasinoDiscord.MessageEditors.MessageEditor;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckIfChannelExistsManager extends ListenerAdapter {

    @Autowired
    ChanelsRepo chanelsRepo;

    private final JDBCChannels jdbcChannels;
    @Autowired
    MessageEditor messageEditor;

    public CheckIfChannelExistsManager(JDBCChannels jdbcChannels) {
        this.jdbcChannels = jdbcChannels;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        int a = jdbcChannels.checkIfChannelExists(event.getChannel().getId());
        System.out.println(a);
    }
}
