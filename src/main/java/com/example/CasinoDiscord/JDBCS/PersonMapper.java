package com.example.CasinoDiscord.JDBCS;

import com.example.CasinoDiscord.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setName(rs.getString("username"));
        return person;
    }
}
