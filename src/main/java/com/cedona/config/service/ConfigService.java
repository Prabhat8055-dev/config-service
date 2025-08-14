package com.cedona.config.service;

import com.cedona.config.entity.ApiEndpointMaster;
import com.cedona.config.request.CreateConfigRequest;
import com.cedona.config.request.RegisterApiEndpointRequest;
import com.cedona.config.response.CreateConfigResponse;
import com.cedona.config.response.RegisterApiEndpointResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ConfigService {

    CreateConfigResponse createConfig(CreateConfigRequest createConfigRequest);

    List<ApiEndpointMaster> getAllEndpoints();

    List<RegisterApiEndpointResponse> registerOrUpdateEndpoint(RegisterApiEndpointRequest request);
}
