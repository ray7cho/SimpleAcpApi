package com.cubox.simpleacp.api.cmnuser.service;

import com.cubox.simpleacp.api.common.service.AbstractService;
import com.cubox.simpleacp.api.domain.entity.CmnUser;
import com.cubox.simpleacp.api.cmnuser.repository.CmnUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CmnUserService extends AbstractService<CmnUser, Integer> {

    @Autowired
    private CmnUserRepository repository;

    @Override
    protected JpaRepository<CmnUser, Integer> getRepository() {
        return repository;
    }

    public Optional<CmnUser>findOneByUserId(String userId) {
        return repository.findOneByUserId(userId);
    }

    public List<CmnUser> findByUserNm(String userNm) {
        return repository.findByUserNmIgnoreCaseContaining(userNm);
    }

}
