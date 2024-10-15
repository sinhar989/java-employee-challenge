package com.example.rqchallenge.model;

import java.util.Objects;

public class Employee {

    private String id;
    private String name;
    private String salary;
    private String age;
    private String image;

    public Employee(){

    }

    public Employee(String id, String name, String salary, String age, String image) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.image = image;
    }

    public Employee(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(salary, employee.salary) && Objects.equals(age, employee.age) && Objects.equals(image, employee.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, age, image);
    }
}
