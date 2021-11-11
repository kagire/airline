package com.kagire.config;

import com.kagire.*;
import com.kagire.repository.DepartmentRepository;
import com.kagire.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@TestConfiguration
@PropertySource("classpath:springMail.properties")
public class MockBeanConfig {

    @MockBean
    DepartmentRepository departmentRepository;

    @Bean
    DepartmentDao departmentDao(){
        return new DepartmentDaoJdbc();
    }

    @MockBean
    EmployeeRepository employeeRepository;

    @Bean
    EmployeeDao employeeDao(){
        return new EmployeeDaoJdbc();
    }

    @Bean
    @Autowired
    DepartmentService departmentService(DepartmentDao departmentDao){
        return new DepartmentServiceImpl(departmentDao);
    }

    @Bean
    @Autowired
    EmployeeService employeeService(EmployeeDao employeeDao){
        return new EmployeeServiceImpl(employeeDao);
    }

    @Bean
    SpreadsheetService spreadsheetService(){
        return new SpreadsheetService();
    }

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.protocol}")
    private String protocol;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttls;

    @MockBean JavaMailSender javaMailSender;
}
