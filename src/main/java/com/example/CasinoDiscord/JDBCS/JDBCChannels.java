package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.ChanelUser;
import com.example.CasinoDiscord.domains.User;
import com.example.CasinoDiscord.domains.UserChanelId;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
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
    public int addMoney(String userId, String channelId, float amount) {
        return jdbcTemplate.update("UPDATE chanel_user SET money = money + ? WHERE user_id = ? AND chanel_id = ?", amount, userId, channelId);
    }

    public int checkIfChannelExists(String channelId){
        return jdbcTemplate.update("INSERT INTO channels(chanel_id) SELECT ? WHERE NOT EXISTS (SELECT * FROM channels WHERE chanel_id = ?)", channelId, channelId);
    }

    public int checkIfUserExists(String userId, String username){
        System.out.println("kashkdhsaklhdskladksah");
        return jdbcTemplate.update("INSERT INTO users(user_id, username) SELECT ?, ? WHERE NOT EXISTS (SELECT * FROM users WHERE user_id = ?);", userId, username, userId);
    }



    @Transactional
    public int checkIfChanelUserExists(Guild guild, String userId, String chanelId) {
        Member member = guild.getMemberById(userId);
        UserChanelId id = new UserChanelId(usersRepo.findUserByUserId(userId).get(), chanelsRepo.findChanelByChannelId(chanelId).get());
        if (chanelUserRepo.findChanelUserByUserChanelId(id).isPresent()) return -1;
        ChanelUser chanelUser = new ChanelUser(id, member.getEffectiveName(), 0);
        chanelUserRepo.save(chanelUser);
        return 1;
    }




    public void initializeNewChannelTable(String channelId, String userId, Guild guild){
        if (!chanelsRepo.findChanelByChannelId(channelId).isPresent()) checkIfChannelExists(channelId);
        else {
             checkIfChanelUserExists(guild, userId, channelId);
            return;
        }
        checkIfChannelExists(channelId);

        Chanel chanel = chanelsRepo.findChanelByChannelId(channelId).get();

        List<Member> members = guild.getMembers().stream()
                .filter(member -> !member.getRoles().contains(guild.getBotRole()))
                        .collect(Collectors.toList());

        for (Member member : members) {

            Chanel userChanel = chanel;

            if (usersRepo.findUserByUserId(member.getId()).isEmpty()) {
                checkIfUserExists(member.getId(), member.getEffectiveName());
            }

            User user = usersRepo.findUserByUserId(member.getId()).get();

            chanelUserRepo.save(new ChanelUser(new UserChanelId(user, userChanel), member.getEffectiveName(), 0));


        }

//

    }





}
