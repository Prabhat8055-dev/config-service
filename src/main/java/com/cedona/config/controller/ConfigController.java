package com.cedona.config.controller;


import com.cedona.config.request.CreateConfigRequest;
import com.cedona.config.response.CreateConfigResponse;
import com.cedona.config.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/config")
public class ConfigController {

    @Autowired
    ConfigService configService;

    @PostMapping("/create/createConfig")
    public CreateConfigResponse createConfig(@RequestBody CreateConfigRequest createConfigRequest) {
        CreateConfigResponse createConfigResponse=configService.createConfig(createConfigRequest);
        return createConfigResponse;
    }



}
