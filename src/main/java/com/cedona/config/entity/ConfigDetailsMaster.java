package com.cedona.config.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "config_details_master")
public class ConfigDetailsMaster {

    @Id
    private String id;

    @Indexed(unique = true)
    private String configKey;

    private String configValue;

}
