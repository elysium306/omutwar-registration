package com.omutwar.registration.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BootstrapSuperuserProperties.class)
public class AppConfig {
}
