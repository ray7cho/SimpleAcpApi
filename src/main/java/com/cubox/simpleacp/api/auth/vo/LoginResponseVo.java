package com.cubox.simpleacp.api.auth.vo;

import com.cubox.simpleacp.api.domain.entity.CmnUser;
import com.cubox.simpleacp.api.domain.entity.CmnUserToken;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class LoginResponseVo {

    @ApiModelProperty(value="사용자 정보")
    CmnUser user;

    @ApiModelProperty(value="사용자 토큰")
    private CmnUserToken userToken;

}
