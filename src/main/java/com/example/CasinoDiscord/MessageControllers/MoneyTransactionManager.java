package com.example.CasinoDiscord.MessageControllers;

import com.example.CasinoDiscord.CasinoDiscordApplication;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import com.example.CasinoDiscord.MessageEditors.MessageEditor;
import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.User;
import com.example.CasinoDiscord.domains.UserChanelId;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

public class MoneyTransactionManager extends ListenerAdapter {

    private final MessageEditor messageEditor;

    private final JDBCChannels jdbcChannels;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    ChanelsRepo chanelsRepo;




    public MoneyTransactionManager(MessageEditor messageEditor, JDBCChannels jdbcChannels) {
        this.messageEditor = messageEditor;

        this.jdbcChannels = jdbcChannels;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split("\\s+");
        if (message.length < 3 || !message[0].equals(CasinoDiscordApplication.MessageStartingKey + "pay")) return;
        float amount = Float.parseFloat(message[2]);
        String clearId = messageEditor.createClearNameId(message[1]);
        UserChanelId sender = new UserChanelId(usersRepo.findUserByUserId(event.getMember().getId()).get(), chanelsRepo.findChanelByChannelId(event.getGuild().getId()).get());
        UserChanelId receiver = new UserChanelId(usersRepo.findUserByUserId(clearId).get(), chanelsRepo.findChanelByChannelId(event.getGuild().getId()).get());
        if (!jdbcChannels.checkSufficientFunds(sender, amount)) return;
        jdbcChannels.pay(sender, receiver, amount);



//        System.out.println(jdbcPerson.payMoney(messageEditor.createClearNameId(event.getAuthor().getId()), messageEditor.createClearNameId(message[1]), Float.parseFloat(message[2]), event));
    }
}
