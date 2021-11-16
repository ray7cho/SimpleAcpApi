package com.cubox.simpleacp.api.cmncd.repository;

import com.cubox.simpleacp.api.domain.entity.CmnCd;
import com.cubox.simpleacp.api.domain.entity.CmnCdId;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import java.util.List;
import java.util.Optional;

public interface CmnCdRepository extends JpaRepository<CmnCd, Integer> {

    Optional<CmnCd> findOneByCmnCdId(CmnCdId cmnCdId);

    // 부모 코드로 하위 메뉴 검색
    List<CmnCd> findByCmnCdId_upperCdOrderBySortOrdr(String upperCd);

}
