package com.example.CasinoDiscord;

import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.ChanelUser;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
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


    @Test
    @Commit
    @Transactional
    void test() {
        Chanel chanel = repo.findChanelByChannelId("first").get();
        ChanelUser user = new ChanelUser("5455", "John", 0.0f);
        user.setChanel(chanel);
        chanel.getChannelUser().add(user);


    }
}
