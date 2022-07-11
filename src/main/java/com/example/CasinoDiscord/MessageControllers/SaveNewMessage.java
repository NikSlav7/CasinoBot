package com.example.CasinoDiscord.MessageControllers;

import com.example.CasinoDiscord.dataSaving.SaveMessage;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.SavedMessagesRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;

public class SaveNewMessage extends ListenerAdapter {

    private final SavedMessagesRepo savedMessagesRepo;

    private final ChanelsRepo chanelsRepo;

    private final UsersRepo usersRepo;

    public SaveNewMessage(SavedMessagesRepo savedMessagesRepo, ChanelsRepo chanelsRepo, UsersRepo usersRepo) {
        this.savedMessagesRepo = savedMessagesRepo;
        this.chanelsRepo = chanelsRepo;
        this.usersRepo = usersRepo;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        SaveMessage saveMessage = new SaveMessage(
                UUID.randomUUID().toString(),
                message,
                usersRepo.findUserByUserId(event.getMember().getId()).get(),
                chanelsRepo.findChanelByChannelId(event.getGuild().getId()).get(),
                new Date(System.currentTimeMillis())

        );
        savedMessagesRepo.save(saveMessage);


    }
}
