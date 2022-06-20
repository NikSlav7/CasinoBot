package com.example.CasinoDiscord.Game;


import com.example.CasinoDiscord.CasinoDiscordApplication;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import com.example.CasinoDiscord.JDBCS.JDBCRoulette;
import com.example.CasinoDiscord.domains.*;
import com.example.CasinoDiscord.messageSender.MessageSender;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.Locale;
import java.util.UUID;

public class Roulette extends ListenerAdapter {


    private final UsersRepo usersRepo;

    private final JDBCRoulette jdbcRoulette;

    private final JDBCChannels jdbcChannels;

    private final RouletteBetDeterminator betDeterminator;

    private final ChanelUserRepo chanelUserRepo;

    private final ChanelsRepo chanelsRepo;

    private final MessageSender rouletteMessages;

    public Roulette(UsersRepo usersRepo, JDBCRoulette jdbcRoulette, JDBCChannels jdbcChannels, RouletteBetDeterminator betDeterminator, ChanelUserRepo chanelUserRepo, ChanelsRepo repo, MessageSender rouletteMessages) {
        this.usersRepo = usersRepo;
        this.jdbcRoulette = jdbcRoulette;
        this.jdbcChannels = jdbcChannels;
        this.betDeterminator = betDeterminator;
        this.chanelUserRepo = chanelUserRepo;
        this.chanelsRepo = repo;
        this.rouletteMessages = rouletteMessages;
    }

    @Override
    @Transactional
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] words = event.getMessage().getContentRaw().split("\\s+");
        if (words.length < 3 || !words[0].equalsIgnoreCase(CasinoDiscordApplication.MessageStartingKey + "Roulette")) return;


        RouletteBet rouletteBet = betDeterminator.determineRouletteBet(words[2]);

        if(rouletteBet.getNumber() == -1 && rouletteBet.getColor() == 0 && rouletteBet.getSize() == 0 && rouletteBet.getEven() == 0) return;
        jdbcRoulette.startTheGame(event.getGuild().getId());
        rouletteBet.setId(new RouletteId(usersRepo.findUserByUserId(event.getMember().getId()).get(), UUID.randomUUID().toString()));

        System.out.println(rouletteBet.getId().getUser().getUserId() + " " + rouletteBet.getId().getUuid());

        ChanelUser chanelUser = chanelUserRepo.findChanelUserByUserChanelId(new UserChanelId(usersRepo.findUserByUserId(event.getMember().getId()).get(), chanelsRepo.findChanelByChannelId(event.getGuild().getId()).get())).get();

        if (!jdbcChannels.checkSufficientFunds(chanelUser.getId(), Float.parseFloat(words[1]))) {
            return;
        }
        MessageSender.rouletteGameNewBet(event.getChannel(),Float.parseFloat(words[1]), words[2], event.getMember().getAsMention());
        rouletteBet.setMoney(Float.parseFloat(words[1]));
        chanelUser.setMoney(chanelUser.getMoney() - Float.parseFloat(words[1]));
        chanelUserRepo.save(chanelUser);
        System.out.println(chanelUser.getMoney());
        jdbcRoulette.setNewBet(rouletteBet, event.getGuild().getId(), event.getMember().getId());

    }









}
