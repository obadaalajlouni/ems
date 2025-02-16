package com.example.Ems.Employee;

import com.example.Ems.configuration.NotFoundInDatabaseException;
import com.example.Ems.department.Department;
import com.example.Ems.department.DepartmentRepo;
import com.example.Ems.project.Project;
import com.example.Ems.project.ProjectRepo;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.RequestContextFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final DepartmentRepo departmentRepo;
    private final EmployeeRepo employeeRepo;
    private final RequestContextFilter requestContextFilter;
    private final ProjectRepo projectRepo;
    private final Validator validator;



    public List<EmployeeResponse> getAllEmployees() {

        return employeeRepo.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public EmployeeResponse getEmployeeById (Integer id) {
        return employeeRepo.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Empolyee not found with id: " + id));
    }
    public EmployeeResponse save(EmployeeRequest employeeRequest){
//
        Department department =departmentRepo.findById(employeeRequest.getDepartment().getId())
                .orElseThrow(()->new RuntimeException("Department not found" ));

        List<Project> projects = new ArrayList<>();
        for(Integer id : employeeRequest.getProjectId()){
            Project project = projectRepo.findById(id).orElse(null);
            if(project != null){
                projects.add(project);
            }
        }
        validator.validate(employeeRequest);
      Employee employee = Employee.builder()
                .empName(employeeRequest.getEmpName())
                .empSurname(employeeRequest.getEmpSurname())
                .department(department)
                .build();

       employeeRepo.save(employee);
        return mapToResponse(employee);
    }

    public ResponseEntity<?> findById(Integer id) throws NotFoundInDatabaseException {
        Employee employee =employeeRepo.findById(id).orElseThrow(()->new NotFoundInDatabaseException("Employee not found with id: " + id));
        EmployeeResponse employeeResponse =mapToResponse(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);

    }

    public ResponseEntity<?> deleteById(Integer id) throws NotFoundInDatabaseException {

         Employee employee =employeeRepo.findById(id).orElseThrow(()-> new NotFoundInDatabaseException("Project not found"));
        if(employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        employeeRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public EmployeeResponse update(Integer id, EmployeeRequest request) throws NotFoundInDatabaseException{
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(()-> new NotFoundInDatabaseException(" Employee not found"));
        Department department= departmentRepo.findById(request.getDepartment().getId())
                .orElseThrow(()-> new NotFoundInDatabaseException("Department not found"));
        validator.validate(request);
        employee.setEmpName(request.getEmpName());
        employee.setEmpPhone(request.getEmpPhone());
        employee.setEmpEmail(request.getEmpEmail());
        employee.setEmpSurname(request.getEmpSurname());
         employee = employeeRepo.save(employee);
         return  mapToResponse(employee);
    }

    public String deleteEmployee(Integer id) {
        employeeRepo.deleteById(id);
        return "Employee deleted successfully";
    }
    public EmployeeResponse mapToResponse(Employee employee) {
        return  new EmployeeResponse();
    }
}
