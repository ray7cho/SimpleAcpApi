package com.cubox.simpleacp.api.common.controller;

import com.cubox.simpleacp.api.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping({ Constants.API.API_AUTH_PREFIX + Constants.API.API_PROFILE })
public class ProfileController {



}
