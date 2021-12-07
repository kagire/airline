package com.kagire.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kagire.entity.Department;
import com.kagire.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class DepartmentControllerTest {

    private static final String URL = "http://localhost:8081/departments";
    private static final String URL1 = "http://localhost:8081/employees";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void departmentsPage() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(List.of(new Department("1"), new Department("2")))));

        mockMvc.perform(MockMvcRequestBuilders.get("/departments"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("departments"));
        mockServer.verify();
    }

    @Test
    void departmentPage() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL1 + "/dedicated/1")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(List.of(new Employee("", new Date(1), 1, 1)))));

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL + "/1")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(new Department("1"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("department"))
                .andExpect(model().attribute("department", hasProperty("id", is((long)0)
                ))).andExpect(model().attribute("department", hasProperty("name", is("1")
                ))).andExpect(model().attribute("relatedEmployees", hasItem(
                        allOf(
                                hasProperty("id", is((long)0)),
                                hasProperty("name", is(""))
                        )
                )));
        mockServer.verify();
    }

    @Test
    void newDepartmentPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/new"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("new_department"))
                .andExpect(model().attribute("department", hasProperty("id", is((long)0)
                ))).andExpect(model().attribute("department", hasProperty("name", is(nullValue())
                )));
    }

    @Test
    void newDepartment() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString((long)1)));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/departments/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Department("name").toString()))
                .andExpect(status().isFound());
        mockServer.verify();
    }

    @Test
    void updateDepartment() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString((long)1)));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Department("name").toString()))
                .andExpect(status().isFound());
        mockServer.verify();
    }

    @Test
    void deleteDepartment() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL + "/1")))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/departments/1/delete"))
                .andExpect(status().isFound());
        mockServer.verify();
    }
}