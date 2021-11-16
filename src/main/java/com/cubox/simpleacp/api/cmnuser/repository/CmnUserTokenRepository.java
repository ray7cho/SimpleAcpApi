package com.cubox.simpleacp.api.cmnuser.repository;

import com.cubox.simpleacp.api.domain.entity.CmnUserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface CmnUserTokenRepository extends JpaRepository<CmnUserToken, Integer> {

    Optional<CmnUserToken> findOneByToken(String token);

    Optional<CmnUserToken> findOneByTokenAndExpiredDtGreaterThan(String token, Instant instant);

    Optional<CmnUserToken> findOneByTokenAndRefreshTokenAndRefreshExpiredDtGreaterThan(
            String token, String refreshToken, Instant instant);

}
