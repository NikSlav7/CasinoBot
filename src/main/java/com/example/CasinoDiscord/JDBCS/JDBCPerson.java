package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.Exceptions.NoEnoughMoneyException;
import com.example.CasinoDiscord.Person;
import com.example.CasinoDiscord.domains.ChanelUser;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public class JDBCPerson implements PersonJDBCInterface {

    @Autowired
    JdbcTemplate template;

    @Autowired
    ChanelUserRepo repo;


    @Override
    public Person findByName(String name) {

        Person person = template.queryForObject("SELECT * FROM people WHERE username = ?", getMapper(), name);
        return person;

    }

    @Override
    public List<Person> findAll() {
        List<Person> list = template.query("SELECT * FROM people", getMapper());
        return list;
    }

    @Override
    public int addMoney(String userId, float amountOfMoney) {
        System.out.println(userId);
        return template.update("UPDATE people SET money = money + ? WHERE username = ?", amountOfMoney, userId);
    }

    @Override
    @Transactional
    public int payMoney(String senderUserId, String receiverUserId, float amountOfMoney, MessageReceivedEvent event) {
        ChanelUser sender = repo.getChanelUserByUserId(senderUserId).get();
        if (sender.getMoney() < amountOfMoney) {
            throw new NoEnoughMoneyException(event.getChannel());
        };
        int i = template.update("UPDATE chanel_user SET money = money - ? WHERE user_id = ?", amountOfMoney, senderUserId);
        int j = template.update("UPDATE chanel_user SET money = money + ? WHERE user_id = ?", amountOfMoney, receiverUserId);
        return i + j;
    }


    ;
    private RowMapper<Person> getMapper(){
        return new PersonMapper();
    }
}
