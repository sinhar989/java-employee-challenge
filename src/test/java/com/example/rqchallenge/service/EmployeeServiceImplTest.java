package com.example.rqchallenge.service;

import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.model.EmployeeResponse;
import com.example.rqchallenge.restclient.EmployeeClient;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeClient employeeClient;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private List<Employee> employeeListResponse = new ArrayList<>();
    private EmployeeResponse employeeResponse = new EmployeeResponse();

    ResponseEntity<EmployeeResponse> responseEntity ;

    @BeforeEach
    void setUp() {
        employeeListResponse = Stream.of(new Employee("1","Rahul","9500000","23","imageInBytexxx"),
                        new Employee("2","Aj","800000","28","imageInBytexxx"),
                        new Employee("3","Sami","900000","21","imageInBytexxx"),
                        new Employee("4","Natasha","700000","29","imageInBytexxx"))
                .collect(Collectors.toList());
        employeeResponse.setStatus("success");
        employeeResponse.setData(employeeListResponse.toArray(new Employee[0]));
        responseEntity = new ResponseEntity<>(employeeResponse, HttpStatus.ACCEPTED);

    }


    @Test
    void getAllEmployees() {
    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void getHighestSalaryOfEmployees() {
        Mockito.when(employeeClient.getHighestSalaryOfEmployees()).thenReturn(employeeListResponse);
        Integer salary = employeeService.getHighestSalaryOfEmployees();
        assertEquals(9500000, salary);
    }

    @Test
    void getTopTenHighestEarningEmployeeNames() {
        Mockito.when(employeeClient.getTopTenHighestEarningEmployeeNames()).thenReturn(employeeListResponse);
        List<String> highSalaryEmpList = employeeService.getTopTenHighestEarningEmployeeNames();
        assertEquals("Rahul", highSalaryEmpList.get(0));
        assertEquals("Sami", highSalaryEmpList.get(1));
        assertEquals("Aj", highSalaryEmpList.get(2));
        assertEquals("Natasha", highSalaryEmpList.get(3));
    }


   @Test
    public void should_ThrowEmployeeNotFoundException_When_EmployeeNotFound() throws Exception {
        Mockito.when(employeeClient.getEmployeeById("2")).thenReturn(null);
        Exception ex = assertThrows(EmployeeNotFoundException.class, () ->employeeService.getEmployeeById("2"));
    }

/*    @Test
    public void should_ThrowInternalServerException_When_ClientCallFails() throws Exception {
        Exception ex = assertThrows(HttpServerErrorException.InternalServerError.class, () ->employeeService.getHighestSalaryOfEmployees());
    }*/
}