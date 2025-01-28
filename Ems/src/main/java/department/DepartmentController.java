package department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {
    @Autowired
private  DepartmentService departmentService;

    @GetMapping
 public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();

    }
    @PostMapping

    public Department addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);

    }
}
