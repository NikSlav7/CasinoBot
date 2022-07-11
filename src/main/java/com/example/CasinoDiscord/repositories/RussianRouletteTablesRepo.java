package com.example.CasinoDiscord.repositories;

import com.example.CasinoDiscord.RussianRoulette.RussianRouletteTable;
import com.example.CasinoDiscord.RussianRoulette.RussianRouletteTableId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RussianRouletteTablesRepo extends JpaRepository<RussianRouletteTable, String > {
    Optional<RussianRouletteTable> findRussianRouletteTableByRussianRouletteTableId(RussianRouletteTableId id);
}
