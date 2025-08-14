package com.cedona.config.controller;


import com.cedona.config.entity.ApiEndpointMaster;
import com.cedona.config.request.CreateConfigRequest;
import com.cedona.config.request.RegisterApiEndpointRequest;
import com.cedona.config.response.CreateConfigResponse;
import com.cedona.config.response.RegisterApiEndpointResponse;
import com.cedona.config.service.ConfigService;
import com.oms.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/config")
public class ConfigController extends BaseController {

    @Autowired
    ConfigService configService;

    @PostMapping("/process/createConfig")
    public CreateConfigResponse createConfig(@RequestBody CreateConfigRequest createConfigRequest) {
        CreateConfigResponse createConfigResponse=configService.createConfig(createConfigRequest);
        return createConfigResponse;
    }

    @GetMapping("/view/getAllEndpoints")
    public List<ApiEndpointMaster> getAllEndpoints() {
        return configService.getAllEndpoints();
    }

    @PostMapping("/process/registerOrUpdateEndpoint")
    public List<RegisterApiEndpointResponse> registerOrUpdateEndpoint(@RequestBody RegisterApiEndpointRequest request) {
        return configService.registerOrUpdateEndpoint(request);
    }

}
