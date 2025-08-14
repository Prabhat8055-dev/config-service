package com.cedona.config.request;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class RegisterApiEndpointRequest {

    private List<UrlService> services;

    @Data
    public static class UrlService {
        private String serviceName;
        private String basePath;
        private Set<String> endpoints;
        private Set<String> wildcardEndpoints;
    }
}

