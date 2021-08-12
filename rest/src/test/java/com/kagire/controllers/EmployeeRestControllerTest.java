package com.kagire.controllers;

import com.kagire.EmployeeService;
import com.kagire.entity.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeRestController.class)
class EmployeeRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void employeesShouldBeEmpty() throws Exception {
        when(employeeService.findAll()).thenReturn(new ArrayList<>());
        this.mvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    void getEmployeeShouldReturnMockInstance() throws Exception {
        when(employeeService.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new Employee("new", new Date(1), 1, 1)));
        this.mvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":0,\"name\":\"new\",\"dateOfBirth\":\"1970-01-01T00:00:00.001+00:00\",\"salary\":1,\"departmentId\":1}"));
    }

    @Test
    void newEmployeeShouldThrowBadRequest() throws Exception {
        when(employeeService.create(ArgumentMatchers.any())).thenReturn(0L);
        this.mvc.perform(post("/employees"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
    }

    @Test
    void updateEmployeeShouldThrowBadRequest() throws Exception {
        when(employeeService.update(ArgumentMatchers.any())).thenReturn(0L);
        this.mvc.perform(put("/employees"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
    }

    @Test
    void deleteEmployeeShouldBeOk() throws Exception {
        this.mvc.perform(delete("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void countEmployeesShouldReturnZero() throws Exception {
        this.mvc.perform(get("/employees/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    void dedicatedEmployeesShouldBeEmpty() throws Exception {
        when(employeeService.findByDepartmentId(ArgumentMatchers.anyInt())).thenReturn(new ArrayList<>());
        this.mvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

    }
}