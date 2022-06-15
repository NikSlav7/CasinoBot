package com.example.CasinoDiscord.repositories;

import com.example.CasinoDiscord.domains.RouletteBet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouletteBetsRepo extends JpaRepository<RouletteBet, String> {
    Optional<RouletteBet> findRouletteResultsById(String id);
}
