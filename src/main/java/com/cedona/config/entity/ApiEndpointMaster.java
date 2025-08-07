package com.cedona.config.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "api_endpoint_master")
public class ApiEndpointMaster {
    @Id
    private String id;
    @Indexed(unique = true)
    private String serviceName;
    private String basePath;
    private Set<String> endpoints;
    private Set<String> wildcardEndpoints;
}

