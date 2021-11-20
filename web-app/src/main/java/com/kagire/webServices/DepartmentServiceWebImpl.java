package com.kagire.webServices;

import com.kagire.DepartmentService;
import com.kagire.config.PropertiesLoader;
import com.kagire.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class DepartmentServiceWebImpl implements DepartmentService {

    static Properties configuration;

    static {
        try {
            configuration = PropertiesLoader.loadProperties("application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String localAddress = configuration.getProperty("host.address");

    private final static String URL = String.format("%s://%s:%d/departments", "http", localAddress, 8081);

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
