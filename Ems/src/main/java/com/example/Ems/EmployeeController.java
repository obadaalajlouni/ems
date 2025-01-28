package com.example.Ems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public String employee(){
       return   employeeService.employeeService();
    }

    @GetMapping ("employee")
    public List<Employee> getEmployee(){
        return employeeService.employeeList();
    }
    @PostMapping
    public Employee save( @RequestBody Employee employee) {
        return employeeService.save(employee);
    }
    @DeleteMapping
    public void delete(Integer id) {
        employeeService.deleteById(id);
    }

    @GetMapping("{id}")
    public Employee getById(@PathVariable Integer id) {
        return employeeService.findById(id);
    }

    @PutMapping("{id}")
    public Employee updateEmployee (Employee employee) {
        return employeeService.update(employee);
    }



}
