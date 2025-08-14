package com.cedona.config.request;


import lombok.Data;

import java.util.List;

@Data
public class CreateConfigRequest {

    private List<ConfigList> configLists;

    @Data
    public static class ConfigList {

        private String configKey;

        private String configValue;
    }

}
