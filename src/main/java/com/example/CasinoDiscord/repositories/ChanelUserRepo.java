package com.example.CasinoDiscord.repositories;

import com.example.CasinoDiscord.domains.ChanelUser;
import com.example.CasinoDiscord.domains.UserChanelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ChanelUserRepo extends JpaRepository<ChanelUser, UserChanelId > {
    Optional<ChanelUser> findChanelUserByUserChanelId(UserChanelId userChanelId);
}
