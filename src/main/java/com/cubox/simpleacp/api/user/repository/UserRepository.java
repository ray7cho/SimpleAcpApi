package com.cubox.simpleacp.api.user.repository;

import com.cubox.simpleacp.api.domain.entity.CmnUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<CmnUser, Integer> {

    Optional<CmnUser> findOneByUserId(String UserId);

    List<CmnUser> findByUserNmIgnoreCaseContaining(String userNm);

}
