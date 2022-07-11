package com.example.CasinoDiscord.repositories;

import com.example.CasinoDiscord.RussianRoulette.RussianRouletteBet;
import com.example.CasinoDiscord.RussianRoulette.RussianRouletteBetId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RussianRouletteBetsRepo extends JpaRepository<RussianRouletteBet, RussianRouletteBetId> {
    Optional<RussianRouletteBet> findRussianRouletteBetByRussianRouletteBetId(RussianRouletteBetId russianRouletteBetId);
}
