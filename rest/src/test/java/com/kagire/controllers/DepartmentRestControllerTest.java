package com.kagire.controllers;

import com.kagire.DepartmentService;
import com.kagire.entity.Department;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentRestController.class)
class DepartmentRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    void departmentsShouldBeEmpty() throws Exception {
        when(departmentService.findAll()).thenReturn(new ArrayList<>());
        this.mvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    void getDepartmentShouldReturnMockInstance() throws Exception {
        when(departmentService.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new Department("new")));
        this.mvc.perform(get("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":0,\"name\":\"new\"}"));
    }

    @Test
    void newDepartmentShouldReturnBadRequestWhenEmptyButMotWhenFilled() throws Exception {
        this.mvc.perform(post("/departments"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));

        this.mvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Department("name").toString()))
                .andExpect(status().isCreated());
    }

    @Test
    void updateDepartmentShouldThrowBadRequestWhenEmptyButMotWhenFilled() throws Exception {
        when(departmentService.update(ArgumentMatchers.any())).thenReturn(0L);
        this.mvc.perform(put("/departments"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));

        this.mvc.perform(put("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Department("name").toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    void deleteDepartmentShouldBeOk() throws Exception {
        this.mvc.perform(delete("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void countDepartmentsShouldReturnZero() throws Exception {
        this.mvc.perform(get("/departments/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }
}