package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.ChanelUser;
import com.example.CasinoDiscord.domains.User;
import com.example.CasinoDiscord.domains.UserId;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class JDBCChannels implements ChannelsJDBC {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ChanelUserRepo chanelUserRepo;

    @Autowired
    ChanelsRepo chanelsRepo;

    @Autowired
    UsersRepo usersRepo;

    @Override
    public int addMoney(String id, float amount) {
        System.out.println(id + " " + amount);
        return jdbcTemplate.update("UPDATE chanel_user SET money = money + ? WHERE user_id = ?", amount, id);
    }

    public int checkIfChannelExists(String channelId){
        return jdbcTemplate.update("INSERT INTO channels(chanel_id) VALUES (?)", channelId);
    }

    public int checkIfUserExists(String userId, String username){
        System.out.println("kashkdhsaklhdskladksah");
        return jdbcTemplate.update("INSERT INTO users(user_id, username) VALUES (?, ?)", userId, username);
    }

//    public void checkIfChannelUserExists(String id, String username, Chanel chanel){
//       Optional<ChanelUser> chanelUser = chanelUserRepo.getChanelUserByUserId(id);
//        System.out.println(chanelUser.get()!=null);
//       if (!chanelUser.isPresent()) {
//           System.out.println(" b");
//           ChanelUser user = new ChanelUser(id, username, 0);
//           chanelUserRepo.save(user);
//           user.setChanel(chanel);
//           chanel.getChannelUser().add(user);
//
//       }
//
//    }
    @Transactional
    public void initializeNewChannelTable(String channelId, Guild guild){
        if (chanelsRepo.findChanelByChannelId(channelId).isPresent()) return;
        checkIfChannelExists(channelId);
        Chanel chanel = chanelsRepo.findChanelByChannelId(channelId).get();
        List<ChanelUser> users = guild.getMembers().stream()
                .filter(member -> !member.getRoles().contains(guild.getBotRole()))
                .map(m -> new ChanelUser(new UserId(new User(m.getId(), m.getEffectiveName()), chanel), m.getEffectiveName(), 0))
                .collect(Collectors.toList());
        users.stream().forEach(
                c -> checkIfUserExists(c.getId().getUser().getUserId(), c.getUsername())
        );

        chanel.setChannelUser(users);
        usersRepo.saveAll(users.stream().map(
                m -> new User(m.getId().getUser().getUserId(), m.getUsername())
        )
                .collect(Collectors.toList()));
        chanelsRepo.save(chanel);
        chanelUserRepo.saveAll(users);

    }
    




}
