package com.kagire.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.kagire")
@EnableJpaRepositories("com.kagire")
public class WebAppConfig {
}
