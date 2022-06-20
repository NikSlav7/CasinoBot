package com.example.CasinoDiscord;

import com.example.CasinoDiscord.Game.RandomResultGenerator;
import com.example.CasinoDiscord.domains.*;
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

    @Autowired
    RandomResultGenerator generator;



    @Test
    @Commit
    @Transactional
    void test() {
//
        BetResult a = generator.generateBetResult();
        System.out.println(a.toString());



    }
}
