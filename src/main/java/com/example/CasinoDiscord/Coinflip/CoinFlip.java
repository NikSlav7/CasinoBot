package com.example.CasinoDiscord.Coinflip;


import com.example.CasinoDiscord.CasinoDiscordApplication;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import com.example.CasinoDiscord.domains.ChanelUser;
import com.example.CasinoDiscord.domains.UserChanelId;
import com.example.CasinoDiscord.messageSender.MessageSender;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import com.iwebpp.crypto.TweetNaclFast;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Random;

@Component
public class CoinFlip extends ListenerAdapter {

    String HEADS_URL = "https://w7.pngwing.com/pngs/47/715/png-transparent-gold-coin-royal-australian-mint-silver-coin-gold-coin-head-gold.png";
    String TAIL_URL = "https://images.vexels.com/media/users/3/264568/isolated/preview/d533cbf30239ebb11e0396fbdc602f65-half-dollar-illustration-coin-tail.png";


    private final UsersRepo usersRepo;

    private final ChanelsRepo chanelsRepo;

    private final ChanelUserRepo chanelUserRepo;

    private final JDBCChannels jdbcChannels;

    public CoinFlip(UsersRepo usersRepo, ChanelsRepo chanelsRepo, ChanelUserRepo chanelUserRepo, JDBCChannels jdbcChannels) {
        this.usersRepo = usersRepo;
        this.chanelsRepo = chanelsRepo;
        this.chanelUserRepo = chanelUserRepo;
        this.jdbcChannels = jdbcChannels;
    }


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split("\\s+");
        if (message.length < 3 || !message[0].equalsIgnoreCase(CasinoDiscordApplication.MessageStartingKey + "coinflip") || (!message[2].equalsIgnoreCase("heads") && !message[2].equalsIgnoreCase("tails"))) {
            return;
        }
        ChanelUser chanelUser = chanelUserRepo.findChanelUserByUserChanelId(new UserChanelId(usersRepo.findUserByUserId(event.getMember().getId()).get(), chanelsRepo.findChanelByChannelId(event.getGuild().getId()).get())).get();

        int winChance = chanelUser.getCoinFlipWinChance();
        float betAmount = Float.parseFloat(message[1]);

        if (!jdbcChannels.checkSufficientFunds(chanelUser.getUserChanelId(), betAmount)) {
            MessageSender.insufficientBalance(event.getChannel(), betAmount, chanelUser.getMoney());
            return;
        }
        String lostPic = message[2].equalsIgnoreCase("heads") ? TAIL_URL : HEADS_URL;
        String winPic = message[2].equalsIgnoreCase("tails") ? TAIL_URL : HEADS_URL;


        boolean win = new Random().nextInt(100) <= winChance;

        if (win) {
            chanelUser.upgradeCoinFlipWinChance();
            won(event.getChannel(), chanelUser, betAmount, winPic);
        }
        else {
            chanelUser.setCoinFlipWinChanceToDefault();
            lost(event.getChannel(), chanelUser, betAmount, lostPic);
        }
        chanelUserRepo.save(chanelUser);


    }
    public void lost(MessageChannel messageChannel, ChanelUser chanelUser, float bet, String picUrl) {
        chanelUser.setMoney(chanelUser.getMoney() - bet);
        MessageSender.coinFlipLost(messageChannel, picUrl, bet, chanelUser.getUsername(), chanelUser.getCoinFlipWinChance());
    }
    public void won(MessageChannel messageChannel, ChanelUser chanelUser, float bet, String picUrl) {
        chanelUser.setMoney(chanelUser.getMoney() + bet);
        MessageSender.coinFlipWon(messageChannel, picUrl, bet, chanelUser.getUsername(), chanelUser.getCoinFlipWinChance());
    }

}
