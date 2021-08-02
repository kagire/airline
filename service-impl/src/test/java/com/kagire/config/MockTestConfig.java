package com.kagire.config;

import com.kagire.DepartmentDao;
import com.kagire.DepartmentDaoJdbc;
import com.kagire.EmployeeDao;
import com.kagire.EmployeeDaoJdbc;
import com.kagire.repository.DepartmentRepository;
import com.kagire.repository.EmployeeRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MockTestConfig {

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

}
