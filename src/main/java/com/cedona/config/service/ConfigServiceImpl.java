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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConfigServiceImpl implements ConfigService{

    @Autowired
    ConfigDetailsMasterRepository configDetailsMasterRepository;

    @Autowired
    private ApiEndpointRepository repository;


    @Override
    @Transactional
    public CreateConfigResponse createConfig(CreateConfigRequest createConfigRequest) {

        List<String> keys = createConfigRequest.getConfigLists().stream()
                .map(CreateConfigRequest.ConfigList::getConfigKey)
                .toList();

        Map<String, ConfigDetailsMaster> existingConfigs = configDetailsMasterRepository
                .findByConfigKeyIn(keys)
                .stream()
                .collect(Collectors.toMap(ConfigDetailsMaster::getConfigKey, c -> c));

        List<ConfigDetailsMaster> configsToSave = new ArrayList<>();

        for (CreateConfigRequest.ConfigList configList : createConfigRequest.getConfigLists()) {
            ConfigDetailsMaster config = existingConfigs.get(configList.getConfigKey());

            if (config != null) {
                // Update existing
                config.setConfigValue(configList.getConfigValue());
                configsToSave.add(config);
            } else {
                // Create new
                ConfigDetailsMaster newConfig = new ConfigDetailsMaster();
                newConfig.setConfigKey(configList.getConfigKey());
                newConfig.setConfigValue(configList.getConfigValue());
                configsToSave.add(newConfig);
            }
        }

        configDetailsMasterRepository.saveAll(configsToSave);

        CreateConfigResponse response = new CreateConfigResponse();
        response.setStatus("Configs created/updated successfully");
        return response;
    }


    public List<ApiEndpointMaster> getAllEndpoints() {
        return repository.findAll();
    }

    @Override
    public List<RegisterApiEndpointResponse> registerOrUpdateEndpoint(RegisterApiEndpointRequest request) {
        List<RegisterApiEndpointResponse> responses = new ArrayList<>();

        for (RegisterApiEndpointRequest.UrlService serviceReq : request.getServices()) {
            ApiEndpointMaster existingService = repository.findByServiceName(serviceReq.getServiceName()).orElse(null);

            if (existingService == null) {
                ApiEndpointMaster newService = new ApiEndpointMaster();
                newService.setServiceName(serviceReq.getServiceName());
                newService.setBasePath(serviceReq.getBasePath());
                newService.setEndpoints(new HashSet<>(Optional.ofNullable(serviceReq.getEndpoints()).orElse(Set.of())));
                newService.setWildcardEndpoints(new HashSet<>(Optional.ofNullable(serviceReq.getWildcardEndpoints()).orElse(Set.of())));

                repository.save(newService);

                RegisterApiEndpointResponse resp = new RegisterApiEndpointResponse();
                resp.setStatus("New service registered: " + serviceReq.getServiceName());
                responses.add(resp);

            } else {
                if (serviceReq.getEndpoints() != null) {
                    existingService.getEndpoints().addAll(serviceReq.getEndpoints());
                }
                if (serviceReq.getWildcardEndpoints() != null) {
                    existingService.getWildcardEndpoints().addAll(serviceReq.getWildcardEndpoints());
                }

                repository.save(existingService);

                RegisterApiEndpointResponse resp = new RegisterApiEndpointResponse();
                resp.setStatus("Endpoints updated for service: " + serviceReq.getServiceName());
                responses.add(resp);
            }
        }

        return responses;
    }


}

