package com.example.rqchallenge.controller;

import com.example.rqchallenge.employees.IEmployeeController;
import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
public class EmployeeController implements IEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Override
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
         List<Employee> employeeList = employeeService.getAllEmployees();
         return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
        return null;
    }

    @Override
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @Override
    @GetMapping("/highestSalary")
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        int highestSalary = employeeService.getHighestSalaryOfEmployees();

        return ResponseEntity.status(HttpStatus.OK).body(highestSalary);
    }

    @Override
    @GetMapping("/topTenHighestEarningEmployeeNames")
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        List<String> employeeNamesList = employeeService.getTopTenHighestEarningEmployeeNames();
        return ResponseEntity.status(HttpStatus.OK).body(employeeNamesList);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody Map<String, Object> employeeInput){
       String status = employeeService.createEmployee(employeeInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(String id) {
         Employee employee = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(employee.getName());
    }
}
