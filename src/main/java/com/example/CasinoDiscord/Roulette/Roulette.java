package com.example.CasinoDiscord.Roulette;


import com.example.CasinoDiscord.CasinoDiscordApplication;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import com.example.CasinoDiscord.JDBCS.JDBCRoulette;
import com.example.CasinoDiscord.dataSaving.SaveRouletteBet;
import com.example.CasinoDiscord.domains.*;
import com.example.CasinoDiscord.messageSender.MessageSender;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.SavedRouletteBetsRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.UUID;

public class Roulette extends ListenerAdapter {


    private final UsersRepo usersRepo;

    private final JDBCRoulette jdbcRoulette;

    private final JDBCChannels jdbcChannels;

    private final RouletteBetDeterminator betDeterminator;

    private final ChanelUserRepo chanelUserRepo;

    private final ChanelsRepo chanelsRepo;

    private final MessageSender rouletteMessages;

    private final SavedRouletteBetsRepo savedRouletteBetsRepo;

    public Roulette(UsersRepo usersRepo, JDBCRoulette jdbcRoulette, JDBCChannels jdbcChannels, RouletteBetDeterminator betDeterminator, ChanelUserRepo chanelUserRepo, ChanelsRepo repo, MessageSender rouletteMessages, SavedRouletteBetsRepo savedRouletteBetsRepo) {
        this.usersRepo = usersRepo;
        this.jdbcRoulette = jdbcRoulette;
        this.jdbcChannels = jdbcChannels;
        this.betDeterminator = betDeterminator;
        this.chanelUserRepo = chanelUserRepo;
        this.chanelsRepo = repo;
        this.rouletteMessages = rouletteMessages;
        this.savedRouletteBetsRepo = savedRouletteBetsRepo;
    }

    @Override
    @Transactional
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] words = event.getMessage().getContentRaw().split("\\s+");
        if (words.length < 3 || !words[0].equalsIgnoreCase(CasinoDiscordApplication.MessageStartingKey + "Roulette")) return;

        float bet = Float.parseFloat(words[1]);

        RouletteBet rouletteBet = betDeterminator.determineRouletteBet(words[2]);

        if(rouletteBet.getNumber() == -1 && rouletteBet.getColor() == 0 && rouletteBet.getSize() == 0 && rouletteBet.getEven() == 0) return;
        jdbcRoulette.startTheGame(event.getGuild().getId(), event.getChannel().getId());
        rouletteBet.setId(new RouletteId(usersRepo.findUserByUserId(event.getMember().getId()).get(), UUID.randomUUID().toString()));


        ChanelUser chanelUser = chanelUserRepo.findChanelUserByUserChanelId(new UserChanelId(usersRepo.findUserByUserId(event.getMember().getId()).get(), chanelsRepo.findChanelByChannelId(event.getGuild().getId()).get())).get();

        if (!jdbcChannels.checkSufficientFunds(chanelUser.getId(), bet)) {
            MessageSender.insufficientBalance(event.getChannel(), bet, chanelUser.getMoney());
            return;
        }

        MessageSender.rouletteGameNewBet(event.getChannel(),bet, words[2], event.getMember().getAsMention());
        rouletteBet.setMoney(bet);

        savedRouletteBetsRepo.save(new SaveRouletteBet(UUID.randomUUID().toString(), rouletteBet.getColor(), rouletteBet.getEven(), rouletteBet.getSize(), rouletteBet.getNumber(), rouletteBet.getMoney(), new Date(System.currentTimeMillis()), chanelUser.getId().getUser(), chanelUser.getId().getChanel()));

        chanelUser.setMoney(chanelUser.getMoney() - bet);
        chanelUserRepo.save(chanelUser);
        jdbcRoulette.setNewBet(rouletteBet, event.getGuild().getId(), event.getMember().getId());

    }








}
