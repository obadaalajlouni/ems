package com.example.Ems.Employee;

import com.example.Ems.department.Department;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "EMPLOYEES")
public class Employee {
    @Id
    @Column(name= "empId")
    private Integer empId;
    private String empName;
    private String empSurname;
    @Column(unique=true,name= "Email")
    private String empEmail;
    @Column(unique=true,name= "phone")
    private String empPhone;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Department department;

    @ManyToMany(mappedBy = "employees")
    private List<Employee> employees;

}
