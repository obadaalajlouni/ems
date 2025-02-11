package com.example.Ems.department;

import com.example.Ems.configuration.NotFoundInDatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public void delete(Integer id) throws NotFoundInDatabaseException{
        departmentService.deleteById(id);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateDepartment (@PathVariable Integer id,  @RequestBody DepartmentRequest request)throws NotFoundInDatabaseException {
        return departmentService.update(id, request);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) throws NotFoundInDatabaseException {
        return ResponseEntity.ok(departmentService.findById(id));
    }
}
