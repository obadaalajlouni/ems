package com.example.Ems.department;

import com.example.Ems.Employee.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentRequest {
    @NotEmpty(message = " id should be not empty")
    @NotNull( message =  " id should be not null")
    @NotBlank( message = " please proviide a id")
    private Integer id;
    @NotEmpty(message =  "name should be not empty")
    @NotNull (message =  "name should be not null")
    @NotBlank( message = " please proviide a name ")
    private String name;
    @NotEmpty(message = "description should be not empty")
    @NotNull( message =  "description should be not null")
    @NotBlank( message = " please proviide a description ")
    private String description;
    @NotEmpty(message =  "employees should be not empty")
    @NotNull( message =  " employees should be not null")
    @NotBlank( message = " please proviide a employees ")
    private List<Employee> employees;
}
