package com.example.Ems.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;



    @GetMapping ("employee")
    public ResponseEntity<List<EmployeeResponse >> getEmployee(){
        return ResponseEntity.ok(employeeService.getEmployee)
    }
    @PostMapping
    public ResponseEntity<EmployeeResponse> save( @RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.save(employeeRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<void> delete(@PathVariable  Integer id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity <Employee> getById(@PathVariable Integer id)
    {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PutMapping("{id}")
    public  ResponseEntity<Employee >updateEmployee (@PathVariable Integer id , @RequestBody EmployeeRequest employeeRequest)
    {
        return ResponseEntity.ok( employeeService.update(id ,employeeRequest));
    }



}
