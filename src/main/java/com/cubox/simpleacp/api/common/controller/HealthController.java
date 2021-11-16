package com.cubox.simpleacp.api.common.controller;

import com.cubox.simpleacp.api.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthController {

    @ResponseBody
    @GetMapping(value = {"/health"}, produces = Constants.APPLICATION_JSON_UTF8_VALUE)
    public String hello() {
        return "ok";
    }


}
