package com.kagire.webServices;

import com.kagire.DepartmentService;
import com.kagire.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

public class DepartmentServiceWebImpl implements DepartmentService {

    private final String URL = String.format("%s://%s:%d/departments", "http", "localhost", 8081);

    private final RestTemplate restTemplate;

    @Autowired
    public DepartmentServiceWebImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Department> findAll() {
        ResponseEntity<List<Department>> responseEntity = restTemplate
                .exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Department>>(){});
        return responseEntity.getBody();
    }

    @Override
    public Optional<Department> findById(Long departmentId) {
        ResponseEntity<Department> responseEntity = restTemplate.getForEntity(URL + "/" + departmentId, Department.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

    @Override
    public Long create(Department department) {
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(URL, department, Long.class);
        return responseEntity.getBody();
    }

    @Override
    public Long update(Department department) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Department> entity = new HttpEntity<>(department, headers);
        ResponseEntity<Long> result = restTemplate.exchange(URL, HttpMethod.PUT, entity, Long.class);
        return result.getBody();
    }

    @Override
    public void delete(Long departmentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Department> entity = new HttpEntity<>(headers);
        restTemplate.exchange(URL + "/" + departmentId, HttpMethod.DELETE, entity, Long.class);
    }

    @Override
    public Long count() {
        return null;
    }
}
