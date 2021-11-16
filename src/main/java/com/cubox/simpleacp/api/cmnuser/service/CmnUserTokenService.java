package com.cubox.simpleacp.api.cmnuser.service;


import com.cubox.simpleacp.api.cmnuser.repository.CmnUserTokenRepository;
import com.cubox.simpleacp.api.common.service.AbstractService;
import com.cubox.simpleacp.api.domain.entity.CmnUser;
import com.cubox.simpleacp.api.domain.entity.CmnUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class CmnUserTokenService extends AbstractService<CmnUserToken, Integer> {

    @Autowired
    private CmnUserTokenRepository repository;

    @Value("${simpleacp.tokenTimeoutMinute}")
    private int tokenTimeoutMinute;

    @Value("${simpleacp.refreshTokenTimeoutDay}")
    private int refreshTokenTimeoutDay;

    @Override
    protected JpaRepository<CmnUserToken, Integer> getRepository() {
        return repository;
    }

    public CmnUserToken issueToken(CmnUser cmnUser) {

        String token = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();

        CmnUserToken cmnUserToken = CmnUserToken.builder()
                .cmnUser(cmnUser)
                .token(token)
                .expiredDt(Instant.now().plus(tokenTimeoutMinute, ChronoUnit.MINUTES))
                .refreshToken(refreshToken)
                .refreshExpiredDt(Instant.now().plus(refreshTokenTimeoutDay, ChronoUnit.DAYS))
                .build();

        repository.save(cmnUserToken);

        return cmnUserToken;
    }

    public CmnUserToken refreshToken(CmnUserToken cmnUserToken) {

        String token = UUID.randomUUID().toString();

        cmnUserToken.setToken(token);
        cmnUserToken.setExpiredDt(Instant.now().plus(tokenTimeoutMinute, ChronoUnit.MINUTES));
        cmnUserToken.setRefreshExpiredDt(Instant.now().plus(refreshTokenTimeoutDay, ChronoUnit.DAYS));
        cmnUserToken.setUpdtDt(Instant.now());

        repository.save(cmnUserToken);

        return cmnUserToken;
    }

    public void expireToken(String token) {

        Optional<CmnUserToken> oCmnUserToken = repository.findOneByToken(token);

        if ( oCmnUserToken.isEmpty() ) {
            return;
        }

        CmnUserToken cmnUserToken = oCmnUserToken.get();
        cmnUserToken.setExpiredDt(Instant.now());
        cmnUserToken.setRefreshExpiredDt(Instant.now());
        cmnUserToken.setUpdtDt(Instant.now());

        repository.save(cmnUserToken);

    }

    public Optional<CmnUserToken> findOneByToken(String token) {
        return repository.findOneByTokenAndExpiredDtGreaterThan(token, Instant.now());
    }

    public Optional<CmnUserToken> findOneByRefreshToken(String token, String refreshToken) {
        return repository.findOneByTokenAndRefreshTokenAndRefreshExpiredDtGreaterThan(token, refreshToken, Instant.now());
    }


}
