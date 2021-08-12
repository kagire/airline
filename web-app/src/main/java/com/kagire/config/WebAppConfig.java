package com.kagire.config;

import com.kagire.DepartmentService;
import com.kagire.EmployeeService;
import com.kagire.webServices.DepartmentServiceWebImpl;
import com.kagire.webServices.EmployeeServiceWebImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class WebAppConfig {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate(new SimpleClientHttpRequestFactory());
    }

    @Bean
    DepartmentService departmentService(){
        return new DepartmentServiceWebImpl(restTemplate());
    }

    @Bean
    EmployeeService employeeService(){
        return new EmployeeServiceWebImpl(restTemplate());
    }
}
