package com.example.Ems;

import department.Department;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name= "EMPLOYEES")
public class Employee {
    @Id
    private Integer empId;
    private String empName;
    private String empSurname;
    @Column(unique=true,name= "Email")
    private String empEmail;
    @Column(unique=true,name= "phone")
    private String empPhone;

    @ManyToOne
    private Department department;
}
