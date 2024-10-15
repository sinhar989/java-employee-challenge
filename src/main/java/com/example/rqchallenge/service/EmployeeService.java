package com.example.rqchallenge.service;

import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface EmployeeService {

    public List<Employee> getAllEmployees() throws EmployeeNotFoundException;
    public Employee getEmployeeById(String id) throws EmployeeNotFoundException;
    public Integer getHighestSalaryOfEmployees();
    public List<String> getTopTenHighestEarningEmployeeNames();
    public String createEmployee(Map<String, Object> employeeMap);

    public Employee deleteEmployee(String id);


}
