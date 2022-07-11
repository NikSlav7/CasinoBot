package com.example.CasinoDiscord.MessageControllers;

import com.example.CasinoDiscord.CasinoDiscordApplication;
import com.example.CasinoDiscord.MessageEditors.MessageEditor;
import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.ChanelUser;
import com.example.CasinoDiscord.domains.User;
import com.example.CasinoDiscord.domains.UserChanelId;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import com.example.CasinoDiscord.repositories.UsersRepo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.ListIterator;


@Component
@Service
public class InfoEventManager extends ListenerAdapter {


    private final JDBCChannels channels;
    private final ChanelsRepo chanelsRepo;
    private final ChanelUserRepo chanelUserRepo;

    private final UsersRepo usersRepo;

    public InfoEventManager(JDBCChannels channels, ChanelsRepo chanelsRepo, ChanelUserRepo chanelUserRepo, UsersRepo usersRepo) {
        this.channels = channels;
        this.chanelsRepo = chanelsRepo;
        this.chanelUserRepo = chanelUserRepo;
        this.usersRepo = usersRepo;
    }






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


                List<ChanelUser> chanelUserList = chanelsRepo.findChanelByChannelId(event.getGuild().getId()).get().getChannelUser();
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
            if (words[0].equalsIgnoreCase(CasinoDiscordApplication.MessageStartingKey + "user-info")) {
                String userId = new MessageEditor().createClearNameId(words[1]);
                User user = usersRepo.findUserByUserId(userId).get();
                Chanel chanel = chanelsRepo.findChanelByChannelId(event.getGuild().getId()).get();
                ChanelUser chanelUser = chanelUserRepo.findChanelUserByUserChanelId(new UserChanelId(user, chanel)).get();

                EmbedBuilder builder = new EmbedBuilder();
                builder
                        .setColor(Color.CYAN)
                        .setTitle("User Info")
                        .setAuthor("Casino Bot")
                        .addField("Username", chanelUser.getUsername() , false)
                        .addField("Money", String.valueOf(chanelUser.getMoney()), false);
                event.getChannel().sendMessageEmbeds(builder.build()).queue();

            }
        }
        System.out.println("Info manager tasks");
    }

}
