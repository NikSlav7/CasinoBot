package com.example.CasinoDiscord.MessageControllers;

import com.example.CasinoDiscord.CasinoDiscordApplication;
import com.example.CasinoDiscord.domains.ChanelUser;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;
import java.util.ListIterator;


@Component
@Service
public class InfoEventManager extends ListenerAdapter {


    private final JDBCChannels channels;
    private final ChanelsRepo chanelsRepo;
    private final ChanelUserRepo chanelUserRepo;

    public InfoEventManager(JDBCChannels channels, ChanelsRepo chanelsRepo, ChanelUserRepo chanelUserRepo) {
        this.channels = channels;
        this.chanelsRepo = chanelsRepo;
        this.chanelUserRepo = chanelUserRepo;
    }






    @Transactional
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String fullMessage = event.getMessage().getContentRaw();
        String[] words = fullMessage.split("\\s+");
        if (words.length > 0) {
            if (words[0].equalsIgnoreCase(CasinoDiscordApplication.MessageStartingKey + "all-users")) {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("All users");

                builder.setColor(Color.CYAN);
                builder.setAuthor("Casino Bot");
                String names = "";
                String money = "";


                List<ChanelUser> chanelUserList = chanelsRepo.findChanelByChannelId("first").get().getChannelUser();

                System.out.println(chanelsRepo.findChanelByChannelId("first").get().getChannelId());
                System.out.println(chanelUserList.size());
                ListIterator<ChanelUser> addAllPeopleToMessage = chanelUserList.listIterator();
                while (addAllPeopleToMessage.hasNext()) {
                    ChanelUser chanelUser = addAllPeopleToMessage.next();
                    names += chanelUser.getUsername() + "\n";
                    money += chanelUser.getMoney() + "\n";
                }
                builder.addField("Names", names, true);
                builder.addField("Money", money, true);
                builder.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Tux.svg/1200px-Tux.svg.png");
                event.getChannel().sendMessageEmbeds(builder.build()).queue();
            }
        }


    }

}