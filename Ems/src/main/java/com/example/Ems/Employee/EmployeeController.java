package com.example.Ems.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public String employee(){
       return   employeeService.employeeService();
    }

    @GetMapping ("employee")
    public List<EmployeeResponse > getEmployee(){
        return employeeService.employeeList();
    }
    @PostMapping
    public EmployeeResponse save( @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.save(employeeRequest);
    }
    @DeleteMapping
    public void delete(Integer id) {
        employeeService.deleteById(id);
    }

    @GetMapping("{id}")
    public Employee getById(@PathVariable Integer id)
    {
        return employeeService.findById(id);
    }

    @PutMapping("{id}")
    public Employee updateEmployee (Employee employee)
    {
        return employeeService.update(employee);
    }



}
