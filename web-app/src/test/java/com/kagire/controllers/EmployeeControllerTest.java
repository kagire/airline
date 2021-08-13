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
class EmployeeControllerTest {

    private static final String URL1 = "http://localhost:8081/departments";
    private static final String URL = "http://localhost:8081/employees";

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
    void employeesPage() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(List.of(new Employee("1", new Date(1), 1, 1), new Employee("2", new Date(1), 1, 1)))));

        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("employees"))
                .andExpect(model().attribute("employees", hasItem(
                        allOf(
                                hasProperty("id", is((long)0)),
                                hasProperty("name", is("1"))
                        )
                ))).andExpect(model().attribute("employees", hasItem(
                        allOf(
                                hasProperty("id", is((long)0)),
                                hasProperty("name", is("2"))
                        )
                )));
        mockServer.verify();
    }

    @Test
    void employeePage() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL1)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(List.of(new Department("1"), new Department("2")))));

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL + "/1")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(new Employee("1", new Date(1), 1, 1))));

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("employee"))
                .andExpect(model().attribute("employee", hasProperty("id", is((long)0)
                ))).andExpect(model().attribute("employee", hasProperty("name", is("1")
                ))).andExpect(model().attribute("availableDepartments", hasItem(
                        allOf(
                                hasProperty("id", is((long)0)),
                                hasProperty("name", is("1"))
                        )

                ))).andExpect(model().attribute("availableDepartments", hasItem(
                        allOf(
                                hasProperty("id", is((long)0)),
                                hasProperty("name", is("2"))
                        )
                )));
        mockServer.verify();
    }

    @Test
    void newEmployeePage() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL1)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(List.of(new Department("1"), new Department("2")))));

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("new_employee"))
                .andExpect(model().attribute("employee", hasProperty("id", is((long)0)
                ))).andExpect(model().attribute("employee", hasProperty("name", is(nullValue())
                ))).andExpect(model().attribute("isDepartmentsAccessible", "true"
                )).andExpect(model().attribute("availableDepartments", hasItem(hasProperty("name", is("1")
                ))));
    }

    @Test
    void newEmployee() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString((long)1)));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/employees/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Employee("name", new Date(1), 1, 1).toString()))
                .andExpect(status().isFound());
        mockServer.verify();
    }

    @Test
    void updateEmployee() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString((long)1)));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Employee("name", new Date(1), 1, 1).toString()))
                .andExpect(status().isFound());
        mockServer.verify();
    }

    @Test
    void deleteEmployee() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL + "/1")))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/1/delete"))
                .andExpect(status().isFound());
        mockServer.verify();
    }
}