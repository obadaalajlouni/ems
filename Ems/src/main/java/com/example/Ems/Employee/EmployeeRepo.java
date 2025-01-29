package com.example.Ems.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo  extends JpaRepository<Employee, Integer> {

//    void delete(Integer id);
}
