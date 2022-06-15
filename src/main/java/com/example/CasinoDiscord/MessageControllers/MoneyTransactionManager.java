package com.example.CasinoDiscord.MessageControllers;

import com.example.CasinoDiscord.CasinoDiscordApplication;
import com.example.CasinoDiscord.JDBCS.JDBCPerson;
import com.example.CasinoDiscord.MessageEditors.MessageEditor;
import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.intellij.lang.annotations.JdkConstants;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

public class MoneyTransactionManager extends ListenerAdapter {
    private final JDBCPerson jdbcPerson;
    private final MessageEditor messageEditor;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    ChanelsRepo chanelsRepo;



    public MoneyTransactionManager(JDBCPerson jdbcPerson, MessageEditor messageEditor) {
        this.jdbcPerson = jdbcPerson;
        this.messageEditor = messageEditor;

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split("\\s+");
        if (message.length < 3 || !message[0].equals(CasinoDiscordApplication.MessageStartingKey + "pay")) return;


//        System.out.println(jdbcPerson.payMoney(messageEditor.createClearNameId(event.getAuthor().getId()), messageEditor.createClearNameId(message[1]), Float.parseFloat(message[2]), event));
    }
}
