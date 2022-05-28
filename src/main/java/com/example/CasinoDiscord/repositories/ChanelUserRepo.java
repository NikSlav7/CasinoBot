package com.example.CasinoDiscord.repositories;

import com.example.CasinoDiscord.domains.ChanelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ChanelUserRepo extends JpaRepository<ChanelUser, String > {
    Optional<ChanelUser> getChanelUserById(String id);
}
