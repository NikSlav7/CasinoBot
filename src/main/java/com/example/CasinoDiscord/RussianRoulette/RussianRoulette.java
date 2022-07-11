package com.example.CasinoDiscord.RussianRoulette;

import com.example.CasinoDiscord.CasinoDiscordApplication;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import com.example.CasinoDiscord.JDBCS.JDBCRussianRoulette;
import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.User;
import com.example.CasinoDiscord.domains.UserChanelId;
import com.example.CasinoDiscord.messageSender.MessageSender;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.RussianRouletteBetsRepo;
import com.example.CasinoDiscord.repositories.RussianRouletteTablesRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class RussianRoulette extends ListenerAdapter {

    private final JDBCRussianRoulette jdbcRussianRoulette;

    private final RussianRouletteTablesRepo russianRouletteTablesRepo;

    private final RussianRouletteBetsRepo betsRepo;

    private final UsersRepo usersRepo;

    private final ChanelsRepo chanelsRepo;

    private final JDBCChannels jdbcChannels;

    public RussianRoulette(JDBCRussianRoulette jdbcRussianRoulette, RussianRouletteTablesRepo russianRouletteTablesRepo, RussianRouletteBetsRepo betsRepo, UsersRepo usersRepo, ChanelsRepo chanelsRepo, JDBCChannels jdbcChannels) {
        this.jdbcRussianRoulette = jdbcRussianRoulette;
        this.russianRouletteTablesRepo = russianRouletteTablesRepo;
        this.betsRepo = betsRepo;
        this.usersRepo = usersRepo;
        this.chanelsRepo = chanelsRepo;
        this.jdbcChannels = jdbcChannels;
    }




    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split("\\s+");
        if (message.length < 2 || !message[0].equalsIgnoreCase(CasinoDiscordApplication.MessageStartingKey + "russian-roulette")) return;

        float bet = Float.parseFloat(message[1]);

        Chanel chanel = chanelsRepo.findChanelByChannelId(event.getGuild().getId()).get();
        User user = usersRepo.findUserByUserId(event.getMember().getId()).get();
        RussianRouletteTable russianRouletteTable;


        if (jdbcChannels.checkSufficientFunds(new UserChanelId(user, chanel), bet)) return;


        Optional<RussianRouletteTable> russianRouletteTableOptional = russianRouletteTablesRepo.findRussianRouletteTableByRussianRouletteTableId(new RussianRouletteTableId(chanel));

        if (russianRouletteTableOptional.isEmpty()) russianRouletteTable = jdbcRussianRoulette.startNewGame(new RussianRouletteTableId(chanel),event.getChannel().getId(), event.getMember().getId(), bet, System.currentTimeMillis());
        else russianRouletteTable = russianRouletteTableOptional.get();
        RussianRouletteBetId russianRouletteBetId = new RussianRouletteBetId(russianRouletteTable, user);
        if (betsRepo.findRussianRouletteBetByRussianRouletteBetId(russianRouletteBetId).isPresent()) return;

        if (bet != russianRouletteTable.getTableBet()) {
            MessageSender.russianRouletteWrongBetAmount(event.getChannel() , bet, russianRouletteTable.getTableBet());
            return;
        }




        jdbcRussianRoulette.addBet(russianRouletteBetId, user.getUsername());

    }
}
