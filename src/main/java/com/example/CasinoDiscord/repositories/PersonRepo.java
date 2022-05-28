package com.example.CasinoDiscord.repositories;

import com.example.CasinoDiscord.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Repository
@Component
public interface PersonRepo extends JpaRepository<Person, Integer> {

}
