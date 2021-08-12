package com.kagire.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.kagire")
@EnableJpaRepositories(basePackages = "com.kagire")
public class TestConfig {

}
