package com.example.CasinoDiscord.repositories;

import com.example.CasinoDiscord.dataSaving.SaveRouletteBet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavedRouletteBetsRepo extends JpaRepository<SaveRouletteBet, String> {
    Optional<SaveRouletteBet> findSaveRouletteBetById(String id);
}
