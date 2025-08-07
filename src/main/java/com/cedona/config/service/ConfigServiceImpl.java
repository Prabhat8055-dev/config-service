package com.cedona.config.service;

import com.cedona.config.entity.ApiEndpointMaster;
import com.cedona.config.entity.ConfigDetailsMaster;
import com.cedona.config.repository.ApiEndpointRepository;
import com.cedona.config.repository.ConfigDetailsMasterRepository;
import com.cedona.config.request.CreateConfigRequest;
import com.cedona.config.request.RegisterApiEndpointRequest;
import com.cedona.config.response.CreateConfigResponse;
import com.cedona.config.response.RegisterApiEndpointResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService{

    @Autowired
    ConfigDetailsMasterRepository configDetailsMasterRepository;

    @Autowired
    private ApiEndpointRepository repository;


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

    public List<ApiEndpointMaster> getAllEndpoints() {
        return repository.findAll();
    }

    public RegisterApiEndpointResponse registerOrUpdateEndpoint(RegisterApiEndpointRequest request) {
        ApiEndpointMaster existingService = repository.findByServiceName(request.getServiceName()).orElse(null);

        if (existingService == null) {
            ApiEndpointMaster newService = new ApiEndpointMaster();
            newService.setServiceName(request.getServiceName());
            newService.setBasePath(request.getBasePath());
            newService.setEndpoints(new HashSet<>(request.getEndpoints()));
            newService.setWildcardEndpoints(new HashSet<>(request.getWildcardEndpoints()));

            repository.save(newService);

            RegisterApiEndpointResponse response = new RegisterApiEndpointResponse();
            response.setStatus("New service registered successfully");
            return response;
        } else {
            if (request.getEndpoints() != null) {
                existingService.getEndpoints().addAll(request.getEndpoints());
            }
            if (request.getWildcardEndpoints() != null) {
                existingService.getWildcardEndpoints().addAll(request.getWildcardEndpoints());
            }

            repository.save(existingService);

            RegisterApiEndpointResponse response = new RegisterApiEndpointResponse();
            response.setStatus("Endpoints added to existing service");
            return response;
        }
    }

}
