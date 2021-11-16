package com.cubox.simpleacp.api.user.controller;

import com.cubox.simpleacp.api.common.Constants;
import com.cubox.simpleacp.api.common.vo.ResultVo;
import com.cubox.simpleacp.api.domain.entity.CmnUser;
import com.cubox.simpleacp.api.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping({Constants.API.API_PREFIX + Constants.API.API_USER})
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = {"/list"}, produces = Constants.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="cmnCd", notes="cmnCd")
    public ResultVo<List<CmnUser>> cmnCd(
            @ApiParam(value = "userNm")
            @RequestParam String userNm
    ) {

        List<CmnUser> list = userService.findByUserNm(userNm);

        return ResultVo.ok(list);
    }}




