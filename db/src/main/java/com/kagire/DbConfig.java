package com.kagire;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:db.properties"})
public class DbConfig {

    @Value( "${spring.datasource.url}" )
    private String jdbcUrl;

    @Value( "${spring.datasource.driver-class-name}" )
    private String jdbcClassName;

    @Value( "${spring.datasource.username}" )
    private String username;

    @Value( "${spring.datasource.password}" )
    private String password;

    @Bean
    @Profile("!test")
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(jdbcClassName);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}