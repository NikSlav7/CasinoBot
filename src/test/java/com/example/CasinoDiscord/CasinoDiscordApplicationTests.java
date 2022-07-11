package com.example.CasinoDiscord;

import com.example.CasinoDiscord.Roulette.RandomResultGenerator;
import com.example.CasinoDiscord.JDBCS.JDBCMessage;
import com.example.CasinoDiscord.dataSaving.SaveMessage;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import com.example.CasinoDiscord.repositories.UsersRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.List;

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

    @Autowired
    JDBCMessage jdbcMessage;



    @Test
    @Commit
    @Transactional
    void test() {

       List<SaveMessage> list = jdbcMessage.getChanelUserMessages("683714219962531888", "784453789037494312", 2);

        assert list.size() > 0;


    }
}
