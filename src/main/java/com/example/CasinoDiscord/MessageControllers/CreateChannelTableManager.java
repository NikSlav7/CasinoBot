package com.example.CasinoDiscord.MessageControllers;

import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CreateChannelTableManager extends ListenerAdapter {

    private final JDBCChannels jdbcChannels;

    public CreateChannelTableManager(JDBCChannels jdbcChannels) {
        this.jdbcChannels = jdbcChannels;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        jdbcChannels.initializeNewChannelTable(event.getGuild().getId(), event.getMember().getId(), event.getGuild());
        System.out.println("NEw table init " + event.getGuild().getId());
    }
}
