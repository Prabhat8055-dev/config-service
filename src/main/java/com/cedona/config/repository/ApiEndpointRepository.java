package com.cedona.config.repository;

import com.cedona.config.entity.ApiEndpointMaster;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ApiEndpointRepository extends MongoRepository<ApiEndpointMaster, String> {
    Optional<ApiEndpointMaster> findByServiceName(String serviceName);
    boolean existsByServiceName(String serviceName);
}
