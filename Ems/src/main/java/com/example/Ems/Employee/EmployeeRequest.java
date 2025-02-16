package com.example.Ems.Employee;

import com.example.Ems.department.Department;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeRequest {
    @NotEmpty(message = "empName should be not empty")
    @NotNull(message = "empName should be not null")
    @Size( min = 3 ,max = 20, message = "empName should be max is 20 caracters ")
    @NotBlank( message = " please proviide a empName ")
    private String empName;
    @NotEmpty(message = "empSurname should be not empty")
    @NotNull( message =  "empSurname should be not null")
    @NotBlank( message = " please proviide a empSurname ")
    private String empSurname;
    @NotEmpty(message = "empEmail should be not empty ")
    @NotNull( message = "empEmail should be not null")
    @Email( message = " please proviide a empEmail")
    private String empEmail;
    @NotEmpty(message = "empPhone should be not empty")
    @NotNull(message =  "empPhone should be not null")
    @NotBlank( message = " please proviide a empPhone ")
    private String empPhone;
    @NotEmpty(message = " department should be not empty")
    @NotNull(message =  " department should be not null")
    @NotBlank( message = " please proviide a department ")
    private Department department;
    @NotEmpty(message = "projectId should be not empty")
    @NotNull(message =  " projectId should be not null ")
    @NotBlank( message = " please proviide a projectId")
    private List<Integer> projectId;
 //private Integer department_id;

}
