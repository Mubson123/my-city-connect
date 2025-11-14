package com.mc.oauth.repository;

import com.mc.oauth.models.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Tokens, UUID> {
    Optional<Tokens> findByEmail(String email);
    Boolean existsByEmail(String email);
}
