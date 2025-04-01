package com.example.javawebserver.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class JavaWebServerConfiguration extends Configuration {
    private String template;
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
} 