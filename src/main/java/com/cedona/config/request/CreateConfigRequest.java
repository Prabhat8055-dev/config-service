package com.cedona.config.request;


import lombok.Data;

@Data
public class CreateConfigRequest {

    private String configKey;

    private String configValue;

}
