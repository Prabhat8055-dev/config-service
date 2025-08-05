package com.cedona.config.repository;

import com.cedona.config.entity.ConfigDetailsMaster;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigDetailsMasterRepository extends MongoRepository<ConfigDetailsMaster,String> {

    ConfigDetailsMaster findByConfigKey(String configKey);
}
