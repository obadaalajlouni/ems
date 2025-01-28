package com.example.Ems;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String empName;
    private String empSurname;
    private String empEmail;
    private String empPhone;

 private Integer department;

}
