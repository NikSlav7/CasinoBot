package com.example.CasinoDiscord.repositories;


import com.example.CasinoDiscord.domains.RouletteBetsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouletteBetsTableRepo extends JpaRepository<RouletteBetsTable, String> {
    Optional<RouletteBetsTable> findRouletteBetsTableById(String id);
    int deleteRouletteBetsTableByTimeWhenCreatedLessThan(long lessThan);
}
