package com.example.rqchallenge.controller;

import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.model.EmployeeResponse;
import com.example.rqchallenge.restclient.EmployeeClient;
import com.example.rqchallenge.service.EmployeeService;
import com.example.rqchallenge.service.EmployeeServiceImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;


    String responseJson = "";



    @MockBean
    private EmployeeService employeeService;

    @Autowired
    ObjectMapper objectMapper;

    private List<Employee> employeeListResponse = new ArrayList<>();
    private EmployeeResponse employeeResponse = new EmployeeResponse();

    ResponseEntity<EmployeeResponse> responseEntity ;

    @BeforeEach
    void setUp() {
        employeeListResponse = Stream.of(new Employee("1","Rahul","100000","23","imageInBytexxx"),
                new Employee("2","Aj","800000","28","imageInBytexxx"),
                new Employee("3","Sami","900000","21","imageInBytexxx"),
                new Employee("4","Natasha","700000","29","imageInBytexxx"))
                .collect(Collectors.toList());
        employeeResponse.setStatus("success");
        employeeResponse.setData(employeeListResponse.toArray(new Employee[0]));
        responseEntity = new ResponseEntity<>(employeeResponse, HttpStatus.ACCEPTED);

    }

    @Test
    void getAllEmployees() throws Exception {
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employeeListResponse);
        MvcResult result = mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                        .andReturn();
        String json = result.getResponse().getContentAsString();
        List<Employee> employeeList =objectMapper.readValue(json, new TypeReference<List<Employee>>() {});
        assertNotNull(employeeList);
        assertEquals(4, employeeList.size());
    }

    @Test
    void getEmployeesByNameSearch() {
    }

    @Test
    void getEmployeeById() throws Exception {
        Mockito.when(employeeService.getEmployeeById("1")).thenReturn(new Employee("1","Rahul","100000","23","imageInBytexxx"));
        MvcResult result = mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();

        Employee employeeObject =objectMapper.readValue(json, Employee.class);
        assertNotNull(employeeObject);
        assertEquals("Rahul", employeeObject.getName());
    }

    @Test
    void getHighestSalaryOfEmployees() throws Exception {
        Mockito.when(employeeService.getHighestSalaryOfEmployees()).thenReturn(9500000);
        ResultActions result = mockMvc.perform(get("/highestSalary"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(String.valueOf(9500000))));

    }

    @Test
    void getTopTenHighestEarningEmployeeNames() throws Exception {
        Mockito.when(employeeService.getTopTenHighestEarningEmployeeNames()).thenReturn(Arrays.asList("Rahul","Sami","Aj", "Natasha"));
        MvcResult result = mockMvc.perform(get("/topTenHighestEarningEmployeeNames"))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<String> employeeNames =objectMapper.readValue(json, new TypeReference<List<String>>() {});
        assertNotNull(employeeNames);
        assertEquals(4, employeeNames.size());
        assertEquals("Rahul", employeeNames.get(0));
    }

    @Test
    void createEmployee() throws Exception {
   /*     Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Rahul");
        requestBody.put("age", "35");
        requestBody.put("salary", "9500000");
        String requestJson = objectMapper.writeValueAsString(requestBody);
        Mockito.when(employeeService.createEmployee(requestBody)).thenReturn("success");
//        Employee employee = new Employee("Rahul", "35","9500000");
        //        String requestJson = objectMapper.writeValueAsString(employee);
        MvcResult result = mockMvc.perform(
                post("/create")
                        .content(requestBody.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn();
        String responseJson = result.getResponse().getContentAsString();
  //      assertEquals("success", responseJson);*/
    }

    @Test
    void deleteEmployeeById() {
    }

   /* @Test
    public void should_Return404_When_UserNotFound() throws Exception {
     //   Mockito.when(employeeService.getEmployeeById("2")).thenReturn(null);
        mockMvc.perform(get("/employees/2"))
                .andExpect(status().isNotFound());
    }*/
}