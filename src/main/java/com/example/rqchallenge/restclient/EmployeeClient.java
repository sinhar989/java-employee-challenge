package com.example.rqchallenge.restclient;

import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.model.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class EmployeeClient {


    @Autowired
    RestTemplate restTemplate;

    String host="https://dummy.restapiexample.com";

    //https://dummy.restapiexample.com/api/v1/employees
    public List<Employee> getAllEmployees(){
        String endpoint = "/api/v1/employees";
        String uri= host + endpoint;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);


        //restTemplate exchange
        ResponseEntity<EmployeeResponse> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                EmployeeResponse.class);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()).getData());
    }

    public Employee getEmployeeById(String id){
        String endpoint = "/api/v1/employees/{id}";
        Map<String, String> params = new HashMap<>();
        params.put("id",id);
        String uri= host + endpoint;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
        //restTemplate exchange
        ResponseEntity<EmployeeResponse> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                EmployeeResponse.class,params);
        return Objects.requireNonNull(responseEntity.getBody()).getData()[0];
    }

    public List<Employee> getHighestSalaryOfEmployees() {
        return getAllEmployees();

    }

    public List<Employee> getTopTenHighestEarningEmployeeNames() {
        return getAllEmployees();
    }

    public String createUser(Map<String, Object> employeeMap) {
        String endpoint = "/api/v1/create";
        String uri= host + endpoint;
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(employeeMap, null);
        ResponseEntity<EmployeeResponse> responseEntity = restTemplate.exchange(uri,HttpMethod.POST, request, EmployeeResponse.class);
        return Objects.requireNonNull(responseEntity.getBody()).getStatus();
    }

    public Employee deleteUser(String id) {
        String endpoint = "/api/v1/delete/{id}";
        String uri= host + endpoint;
        Map<String, String> params = new HashMap<>();
        params.put("id",id);
        ResponseEntity<EmployeeResponse> responseEntity = restTemplate.exchange(uri,HttpMethod.DELETE, null, EmployeeResponse.class, params);
        return Objects.requireNonNull(responseEntity.getBody()).getData()[0];
    }
}
