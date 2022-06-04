package com.example.CasinoDiscord;

import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.ChanelUser;
import com.example.CasinoDiscord.domains.User;
import com.example.CasinoDiscord.domains.UserId;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import com.example.CasinoDiscord.repositories.UsersRepo;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

@SpringBootTest
@ComponentScan(basePackages = "com.example.CasinoDiscord")
class CasinoDiscordApplicationTests {

    @Autowired
    ChanelsRepo repo;
    @Autowired
    JDBCChannels channels;
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    ChanelUserRepo chanelUserRepo;




    @Test
    @Commit
    @Transactional
    void test() {
        User nikslav = new User();
        nikslav.setUserId("1234563");
        nikslav.setUsername("nikslav");
        Chanel chanel = new Chanel();
        chanel.setChannelId("testO");
        UserId userId = new UserId();
        userId.setUser(nikslav);
        userId.setChanel(chanel);
        ChanelUser chanelUser = new ChanelUser(userId, nikslav.getUsername(), 0);
        usersRepo.save(nikslav);
        repo.save(chanel);
        chanelUserRepo.save(chanelUser);



    }
}
