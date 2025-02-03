package com.example.Ems.project;

import com.example.Ems.Employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @SequenceGenerator(
            name = "project_id",
            sequenceName = "project_id"
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "project_id")
    private Integer id;


    private String Name;
    private String Description;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name= "employee_id"),
            inverseJoinColumns = @JoinColumn(name ="project_id" )
    )
    private List<Employee> employees;
}
