package com.example.CasinoDiscord.MessageControllers;

import com.example.CasinoDiscord.CasinoDiscordApplication;
import com.example.CasinoDiscord.JDBCS.JDBCMessage;
import com.example.CasinoDiscord.MessageEditors.MessageEditor;
import com.example.CasinoDiscord.messageSender.MessageSender;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class GetUserMessagesManager extends ListenerAdapter {


    private final MessageEditor messageEditor;

    private final JDBCMessage jdbcMessage;

    public GetUserMessagesManager(MessageEditor messageEditor, JDBCMessage jdbcMessage) {
        this.messageEditor = messageEditor;
        this.jdbcMessage = jdbcMessage;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split("\\s+");
        if (message.length < 2 || !message[0].equalsIgnoreCase(CasinoDiscordApplication.MessageStartingKey + "messageInfo")) return;

        int limit = message.length >= 3 ? Integer.parseInt(message[2]) : 5;
        String chanelId = event.getGuild().getId();
        String userId = message.length > 1 ? messageEditor.createClearNameId(message[1]) : event.getMember().getId();



        if (!userId.equals(event.getMember().getId()) && event.getMember().getPermissions().stream().filter(p -> p.getName().equals("ADMINISTRATOR")).collect(Collectors.toList()).size() == 0) return;


        MessageSender.chanelUserMessagesInfo(event.getChannel(), jdbcMessage.getChanelUserMessages(userId, chanelId, limit), "Loh");



    }
}
