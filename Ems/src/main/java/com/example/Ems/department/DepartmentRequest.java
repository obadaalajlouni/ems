package com.example.Ems.department;

import com.example.Ems.Employee.Employee;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentRequest {
    private Integer id;
    private String name;
    private String description;
    private List<Employee> employees;
}
