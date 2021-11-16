package com.cubox.simpleacp.api.auth.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class AuthControllerTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void testLogin() throws Exception {

        MultiValueMap<String, String> parms = null;

        // 로그인
        parms = new LinkedMultiValueMap<>();
        parms.add("userId", "admin");
        parms.add("userPw", "admin");
        MvcResult mvcResult = mocMvc.perform(post("/simpleacp/v1/auth/login")
                .params(parms)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("resultCode").exists())
                .andExpect(jsonPath("resultCode").value("ok"))
                .andDo(print())
                .andReturn()
        ;

        String responseJsonStr = mvcResult.getResponse().getContentAsString();

        JSONObject jsonObj = new JSONObject(responseJsonStr);
        JSONObject jsonObjData = jsonObj.getJSONObject("data");
        JSONObject jsonObjUserToken = jsonObjData.getJSONObject("userToken");
        String token = jsonObjUserToken.getString("token");
        String refreshToken = jsonObjUserToken.getString("refreshToken");

        // 리플레쉬토큰
        parms = new LinkedMultiValueMap<>();
        parms.add("token", token);
        parms.add("refreshToken", refreshToken);
        mocMvc.perform(post("/simpleacp/v1/auth/refreshToken")
                .params(parms)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("resultCode").exists())
                .andExpect(jsonPath("resultCode").value("ok"))
                .andDo(print())
                ;

    }


}