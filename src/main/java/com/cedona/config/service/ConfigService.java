package com.cedona.config.service;

import com.cedona.config.request.CreateConfigRequest;
import com.cedona.config.response.CreateConfigResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface ConfigService {

    public CreateConfigResponse createConfig(CreateConfigRequest createConfigRequest);

}
