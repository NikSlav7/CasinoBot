package com.example.CasinoDiscord.repositories;

import com.example.CasinoDiscord.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<User, String> {
    Optional<User> findUserByUserId(String userId);
}
