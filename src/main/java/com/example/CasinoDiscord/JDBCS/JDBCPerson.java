package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JDBCPerson implements PersonJDBCInterface {

    @Autowired
    JdbcTemplate template;


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

    ;
    private RowMapper<Person> getMapper(){
        return new PersonMapper();
    }
}
