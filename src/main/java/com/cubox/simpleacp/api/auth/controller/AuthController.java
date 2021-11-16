package com.cubox.simpleacp.api.auth.controller;

import com.cubox.simpleacp.api.auth.vo.LoginResponseVo;
import com.cubox.simpleacp.api.cmnuser.service.CmnUserService;
import com.cubox.simpleacp.api.cmnuser.service.CmnUserTokenService;
import com.cubox.simpleacp.api.common.Constants;
import com.cubox.simpleacp.api.common.vo.ResultVo;
import com.cubox.simpleacp.api.domain.entity.CmnUser;
import com.cubox.simpleacp.api.domain.entity.CmnUserToken;
import com.cubox.simpleacp.api.messages.MessageService;
import com.cubox.simpleacp.api.messages.Messages;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping({Constants.API.API_PREFIX + Constants.API.API_AUTH})
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CmnUserService cmnUserService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CmnUserTokenService cmnUserTokenService;

    @GetMapping(value = {"/init_cmn_user"}, produces = Constants.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="초기 관리자 계정 생성", notes="초기 관리자 계정 생성")
    public ResultVo initCmnUser() {

        final var initUserId = "admin";
        final var initUserPw = "admin";

        Optional<CmnUser> oCmnUser = cmnUserService.findOneByUserId(initUserId);
        if ( oCmnUser.isPresent() ) {
            return ResultVo.fail("이미 초기 관리자 계정이 생성되었습니다.");
        }

        CmnUser cmnUser = CmnUser.builder()
                .userId(initUserId)
                .userPw(passwordEncoder.encode(initUserPw))
                .userNm("관리자")
                .build();

        cmnUserService.save(cmnUser);

        return ResultVo.ok();
    }

    @PostMapping(value = {"/login"}, produces = Constants.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="로그인", notes="로그인")
    public ResultVo<LoginResponseVo> login(
            @ApiParam(value = "사용자아이디")
            @RequestParam String userId,
            @ApiParam(value = "사용자비밀번호")
            @RequestParam String userPw
    ) {

        Optional<CmnUser> oCmnUser = cmnUserService.findOneByUserId(userId);
        // 아이디 존재여부체크
        if ( oCmnUser.isEmpty() )
        {
            return ResultVo.fail(messageService.getMessage(Messages.AUTH_USER_NOT_FOUND));
        }

        CmnUser cmnUser = oCmnUser.get();
        if(!passwordEncoder.matches(userPw, cmnUser.getUserPw()))
        {
            return ResultVo.fail(messageService.getMessage(Messages.AUTH_PASSWORDS_DO_NOT_MATCH));
        }

        CmnUserToken cmnUserToken = cmnUserTokenService.issueToken(cmnUser);

        LoginResponseVo loginResponseVo = LoginResponseVo.builder()
                .user(cmnUserToken.getCmnUser())
                .userToken(cmnUserToken)
                .build();

        return ResultVo.ok(loginResponseVo);
    }


    @PostMapping(value = "/logout", produces = Constants.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="로그아웃", notes="로그아웃")
    public ResultVo logout(
            @ApiParam(value = "토큰")
            @RequestParam String token
    ) {
        cmnUserTokenService.expireToken(token);

        return ResultVo.ok();
    }


    @PostMapping(value = {"/refreshToken"}, produces = Constants.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="토큰 갱신", notes="토큰 갱신")
    public ResultVo<CmnUserToken> refreshToken(
            @ApiParam(value = "토큰")
            @RequestParam String token,
            @ApiParam(value = "리플레쉬토킅")
            @RequestParam String refreshToken
    ) {

        Optional<CmnUserToken> oCmnUserToken = cmnUserTokenService.findOneByRefreshToken(token, refreshToken);
        if ( oCmnUserToken.isEmpty())
        {
            return ResultVo.fail("invalid refresh token.");
        }

        CmnUserToken cmnUserToken = oCmnUserToken.get();
        cmnUserToken = cmnUserTokenService.refreshToken(cmnUserToken);

        return ResultVo.ok(cmnUserToken);
    }
}




