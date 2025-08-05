package com.cedona.config.service;

import com.cedona.config.entity.ConfigDetailsMaster;
import com.cedona.config.repository.ConfigDetailsMasterRepository;
import com.cedona.config.request.CreateConfigRequest;
import com.cedona.config.response.CreateConfigResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ConfigServiceImpl implements ConfigService{

    @Autowired
    ConfigDetailsMasterRepository configDetailsMasterRepository;


    @Override
    public CreateConfigResponse createConfig(CreateConfigRequest createConfigRequest) {

        ConfigDetailsMaster configDetailsMaster=new ConfigDetailsMaster();
        configDetailsMaster.setConfigKey(createConfigRequest.getConfigKey());
        configDetailsMaster.setConfigValue(createConfigRequest.getConfigValue());
        configDetailsMasterRepository.save(configDetailsMaster);

        CreateConfigResponse createConfigResponse=new CreateConfigResponse();
        createConfigResponse.setStatus("Config Created");

        return createConfigResponse;
    }
}
