package com.example.rqchallenge.service;

import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.restclient.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private final EmployeeClient employeeClient;

    public EmployeeServiceImpl(EmployeeClient employeeClient) {
        this.employeeClient = employeeClient;
    }

    @Override
    public List<Employee> getAllEmployees() throws EmployeeNotFoundException {
        List<Employee> employeeList = employeeClient.getAllEmployees();
        if(employeeList.size() ==0)
            throw new EmployeeNotFoundException("No Employees Found");
        return employeeClient.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(String id) throws EmployeeNotFoundException {

        Optional<Employee> employeeOptional = Optional.ofNullable(employeeClient.getEmployeeById(id));
        if(!employeeOptional.isPresent()){
            throw new EmployeeNotFoundException("Employee Not Found");
        }
        return employeeClient.getEmployeeById(id) ;
    }

    @Override
    public Integer getHighestSalaryOfEmployees() {
       List<Employee> employeeList = employeeClient.getHighestSalaryOfEmployees();
       List<Integer> salaryList = employeeList.stream().map(Employee::getSalary)
               .map(Integer::parseInt)
               .collect(Collectors.toList());
       Collections.sort(salaryList, Collections.reverseOrder());
       return  salaryList.get(0);

    }

    @Override
    public List<String> getTopTenHighestEarningEmployeeNames() {
        List<Employee> employeeList = employeeClient.getTopTenHighestEarningEmployeeNames();
        List<String> sortedEmployeeList = employeeList.stream().sorted(Comparator.comparing(Employee :: getSalary).reversed())
                .limit(10)
                .map(Employee::getName)
                .collect(Collectors.toList());

   //     System.out.println(sortedEmployeeList.forEach());
        return sortedEmployeeList;

    }

    @Override
    public String createEmployee(Map<String, Object> employeeMap) {
        return employeeClient.createUser(employeeMap);
    }

    @Override
    public Employee deleteEmployee(String id) {
        return employeeClient.deleteUser(id);
    }
}
