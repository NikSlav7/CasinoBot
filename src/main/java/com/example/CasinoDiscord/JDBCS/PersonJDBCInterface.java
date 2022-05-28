package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.Person;

import java.util.List;

public interface PersonJDBCInterface {
    Person findByName(String name);
    List<Person> findAll();

    int addMoney(String userId, float amountOfMoney);
}
