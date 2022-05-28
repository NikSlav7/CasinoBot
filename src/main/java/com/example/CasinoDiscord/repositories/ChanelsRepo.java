package com.example.CasinoDiscord.repositories;

import com.example.CasinoDiscord.domains.Chanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChanelsRepo extends JpaRepository<Chanel, String> {
    Optional<Chanel> findChanelByChannelId(String channelId);
}
