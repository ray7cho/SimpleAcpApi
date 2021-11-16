package com.cubox.simpleacp.api.cmncd.controller;

import com.cubox.simpleacp.api.cmncd.service.CmnCdService;
import com.cubox.simpleacp.api.common.Constants;
import com.cubox.simpleacp.api.common.vo.ResultVo;
import com.cubox.simpleacp.api.domain.entity.CmnCd;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping({Constants.API.API_PREFIX + Constants.API.API_CODE})
public class CmnCdController {

    @Autowired
    private CmnCdService cmnCdService;


    @GetMapping(value = {"/cmnCd"}, produces = Constants.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="cmnCd", notes="cmnCd")
    public ResultVo<List<CmnCd>> cmnCd(
            @ApiParam(value = "upperCd")
            @RequestParam String upperCd
    ) {

        List<CmnCd> list = cmnCdService.findByUpperCd(upperCd);

        return ResultVo.ok(list);
    }}




