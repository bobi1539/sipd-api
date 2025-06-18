package com.suzume.sipd.repository;

import com.suzume.sipd.entity.LogAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface LogAuthRepository extends JpaRepository<LogAuth, Long> {

    Optional<LogAuth> findByRefreshTokenAndRefreshTokenExpiryAfter(String refreshToken, LocalDate date);

}
