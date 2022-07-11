package com.example.CasinoDiscord.repositories;

import com.example.CasinoDiscord.dataSaving.SaveMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavedMessagesRepo extends JpaRepository<SaveMessage, String> {
    Optional<SaveMessage> getSaveMessageByUuid(String uuid);
}
