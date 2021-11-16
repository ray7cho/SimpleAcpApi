package com.cubox.simpleacp.api.cmncd.service;

import com.cubox.simpleacp.api.common.service.AbstractService;
import com.cubox.simpleacp.api.domain.entity.CmnCd;
import com.cubox.simpleacp.api.cmncd.repository.CmnCdRepository;
import com.cubox.simpleacp.api.domain.entity.CmnCdId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CmnCdService extends AbstractService<CmnCd, Integer> {

    @Autowired
    private CmnCdRepository repository;

    @Override
    protected JpaRepository<CmnCd, Integer> getRepository() {
        return repository;
    }

    public Optional<CmnCd> findOneByUpperCd(CmnCdId cmnCdId) {
        return repository.findOneByCmnCdId(cmnCdId);
    }

    public List<CmnCd> findByUpperCd(String upperCd) {
        return repository.findByCmnCdId_upperCdOrderBySortOrdr(upperCd);
    }

    public void delete(CmnCd cmnCd) {
        repository.delete(cmnCd);
    }

}
