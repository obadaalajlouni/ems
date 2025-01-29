package com.example.Ems.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {
    @Autowired
private  DepartmentService departmentService;

    @GetMapping
 public List<DepartmentResponse> getAllDepartments() {
        return departmentService.departments();

    }
    @PostMapping

    public DepartmentResponse addDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.addDepartment(departmentRequest);

    }
    @DeleteMapping
    public void delete(Integer id) {
        departmentService.deleteById(id);
    }
    @PutMapping("{id}")
    public Department updateDepartment (Department department) {
        return departmentService.update(department);
    }
}
