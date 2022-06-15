package com.example.CasinoDiscord.MessageControllers;

import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import com.example.CasinoDiscord.MessageEditors.MessageEditor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;

@ComponentScan(basePackages = {"com.example.CasinoDiscord"})
public class AddMoneyManager extends ListenerAdapter {

    private final JDBCChannels jdbcChannels;

    private final MessageEditor messageEditor;

    public AddMoneyManager(JDBCChannels jdbcChannels, MessageEditor messageEditor) {
        this.jdbcChannels = jdbcChannels;
        this.messageEditor = messageEditor;
    }


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String fullMessage = event.getMessage().getContentRaw();
        String[] words = fullMessage.split("\\s+");
        if (words.length >= 3 && words[0].equalsIgnoreCase("$add-money")) {
            jdbcChannels.addMoney(messageEditor.createClearNameId(words[1]), event.getGuild().getId(), Float.parseFloat(words[2]));
            System.out.println(messageEditor.createClearNameId(words[1]) + " " + Float.parseFloat(words[2]));
        }
    }
}
