package com.example.CasinoDiscord.MessageControllers;

import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.CasinoDiscord"})
public class AddMoneyManager extends ListenerAdapter {

    private final JDBCChannels jdbcChannels;

    public AddMoneyManager(JDBCChannels jdbcChannels) {
        this.jdbcChannels = jdbcChannels;
    }


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String fullMessage = event.getMessage().getContentRaw();
        String[] words = fullMessage.split("\\s+");
        if (words.length >= 3 && words[0].equalsIgnoreCase("$add-money")) {
            jdbcChannels.addMoney(words[1], Float.parseFloat(words[2]));
        }
    }
}
