package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("env.variables")
public class EnvVariables {
    private String property1;
    private String property2;
    private String property3;
    private String property4;
    private String authAddress;
}
