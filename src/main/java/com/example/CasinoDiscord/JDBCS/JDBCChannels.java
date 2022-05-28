package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.ChanelUser;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class JDBCChannels implements ChannelsJDBC {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ChanelUserRepo chanelUserRepo;

    @Override
    public int addMoney(String id, float amount) {
        System.out.println(id + " " + amount);
        return jdbcTemplate.update("UPDATE chanel_user SET money = money + ? WHERE user_id = ?", amount, id);
    }

    public int checkIfChannelExists(String id){
        return jdbcTemplate.update("INSERT INTO channels(id) VALUES (?)", id);
    }

    public void checkIfChannelUserExists(String id, String username, Chanel chanel){
       Optional<ChanelUser> chanelUser = chanelUserRepo.getChanelUserById(id);
        System.out.println(chanelUser.get()!=null);
       if (!chanelUser.isPresent()) {
           System.out.println(" b");
           ChanelUser user = new ChanelUser(id, username, 0);
           chanelUserRepo.save(user);
           user.setChanel(chanel);
           chanel.getChannelUser().add(user);

       }

    }



}
