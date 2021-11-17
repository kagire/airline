package com.kagire;

import com.kagire.config.MockBeanConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MailServiceImpl.class)
@ContextConfiguration(classes = MockBeanConfig.class)
class MailServiceImplTest {

    @Autowired
    MailService mailService;

    @MockBean
    DepartmentService departmentService;

    @MockBean
    EmployeeService employeeService;

    @MockBean
    SpreadsheetService spreadsheetService;

    @Test
    void sendInfoEmail() {
        Assertions.assertDoesNotThrow(() -> mailService.sendInfoEmail("s"));
    }
}