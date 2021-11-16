package com.cubox.simpleacp.api;

import com.cubox.simpleacp.api.cmncd.service.CmnCdService;
import com.cubox.simpleacp.api.domain.entity.CmnCd;
import com.cubox.simpleacp.api.domain.entity.CmnCdId;
import com.cubox.simpleacp.api.cmnmenu.repository.CmnMenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;

@Transactional
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
class CmnCdIdTests {

    @Autowired
    CmnMenuRepository cmnMenuRepository;

    @Autowired
    CmnCdService cmnCdService;

    @Test
    void testCmnCdId() {
        String upperCd = "M9900";
        String esntlCd = "M9901";

        CmnCdId cmnCdId = new CmnCdId();
        cmnCdId.setUpperCd(upperCd);
        cmnCdId.setEsntlCd(esntlCd);

        CmnCd cmnCd = new CmnCd();
        cmnCd.setCmnCdId(cmnCdId);
        cmnCd.setCdNm("코드1");

        // INSERT
        cmnCdService.save(cmnCd);

        // SELECT ONE
        Optional<CmnCd> one = cmnCdService.findOneByUpperCd(cmnCdId);
        Assert.assertTrue(one.isPresent());
        Assert.assertTrue("코드1".equals(one.get().getCdNm()));

        // UPDATE
        cmnCd.setCdNm("코드2");
        cmnCdService.save(cmnCd);

        one = cmnCdService.findOneByUpperCd(cmnCdId);
        Assert.assertTrue("코드2".equals(one.get().getCdNm()));

        // SELECT LIST
        List<CmnCd> list = cmnCdService.findByUpperCd(upperCd);
        Assert.assertTrue(list.size() == 1);

        // DELETE
        cmnCdService.delete(cmnCd);
        list = cmnCdService.findByUpperCd(upperCd);
        Assert.assertTrue(list.size() == 0);



    }

}
