package department;

import com.example.Ems.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;
 public List<Department> getAllDepartments() {
     return departmentRepo.findAll();
 }
 public Department getDepartmentById(Integer id) {
     return departmentRepo.findById(id).get();
 }
 public Department addDepartment(Department department) {
     return departmentRepo.save(department);

 }

    public void deleteById(Integer id) {
        departmentRepo.deleteById(id);
    }
    public Department update(Department department){
        return departmentRepo.save(department);
    }
}
