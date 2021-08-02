package com.kagire.config;

import com.kagire.DepartmentDaoJdbc;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.kagire")
@EnableJpaRepositories(basePackages = "com.kagire")
@PropertySource({"classpath:db.properties"})
@Import({DepartmentDaoJdbc.class, DepartmentDaoJdbc.class})
public class TestConfig {

}
