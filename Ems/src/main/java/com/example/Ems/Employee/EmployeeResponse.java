package com.example.Ems.Employee;

import com.example.Ems.department.Department;
import lombok.Data;

@Data
public class EmployeeResponse {
    private String empName;
    private String empSurname;
    private String empEmail;
    private String empPhone;
    private Department department;
    //private Integer department_id;
}
