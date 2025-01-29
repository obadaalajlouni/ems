package com.example.Ems.department;

import com.example.Ems.Employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(
            generator ="department_id",
strategy = GenerationType.SEQUENCE
    )
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "empId")
    private List<Employee> employees;
}
