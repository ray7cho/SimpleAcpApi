package com.cubox.simpleacp.api.user.service;

import com.cubox.simpleacp.api.common.service.AbstractService;
import com.cubox.simpleacp.api.domain.entity.CmnUser;
import com.cubox.simpleacp.api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends AbstractService<CmnUser, Integer> {

    @Autowired
    private UserRepository repository;

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
