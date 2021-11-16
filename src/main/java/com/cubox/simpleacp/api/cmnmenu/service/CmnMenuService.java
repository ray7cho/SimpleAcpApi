package com.cubox.simpleacp.api.cmnmenu.service;

import com.cubox.simpleacp.api.common.service.AbstractService;
import com.cubox.simpleacp.api.domain.entity.CmnMenu;
import com.cubox.simpleacp.api.cmnmenu.repository.CmnMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CmnMenuService extends AbstractService<CmnMenu, Integer> {

    @Autowired
    private CmnMenuRepository repository;

    @Override
    protected JpaRepository<CmnMenu, Integer> getRepository() {
        return repository;
    }


}
