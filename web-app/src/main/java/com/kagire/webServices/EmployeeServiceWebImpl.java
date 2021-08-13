package com.kagire.webServices;

import com.kagire.EmployeeService;
import com.kagire.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceWebImpl implements EmployeeService {

    private final static String URL = String.format("%s://%s:%d/employees", "http", "localhost", 8081);

    private final RestTemplate restTemplate;
    @Autowired
    public EmployeeServiceWebImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Employee> findAll() {
        ResponseEntity<List<Employee>> responseEntity = restTemplate
                .exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>(){});
        return responseEntity.getBody();
    }

    @Override
    public Optional<Employee> findById(Long employeeId) {
        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(URL + "/" + employeeId, Employee.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

    @Override
    public Long create(Employee employee) {
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(URL, employee, Long.class);
        return responseEntity.getBody();
    }

    @Override
    public Long update(Employee employee) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Employee> entity = new HttpEntity<>(employee, headers);
        ResponseEntity<Long> result = restTemplate.exchange(URL, HttpMethod.PUT, entity, Long.class);
        return result.getBody();
    }

    @Override
    public void delete(Long employeeId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Employee> entity = new HttpEntity<>(headers);
        restTemplate.exchange(URL + "/" + employeeId, HttpMethod.DELETE, entity, Long.class);
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        ResponseEntity<List<Employee>> responseEntity = restTemplate
                .exchange(URL + "/dedicated/" + departmentId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>(){});
        return responseEntity.getBody();
    }
}
