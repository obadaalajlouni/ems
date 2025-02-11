package com.example.Ems.Employee;

import com.example.Ems.configuration.NotFoundInDatabaseException;
import com.example.Ems.department.Department;
import com.example.Ems.department.DepartmentRepo;
import com.example.Ems.project.Project;
import com.example.Ems.project.ProjectRepo;
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

//    public String employeeService (){
//        return "Employee Service";
//    }

    public List<EmployeeResponse> getAllEmployees() {
//        List<Employee> employees = employeeRepo.findAll();
//        List<EmployeeResponse> employeesResponse = new ArrayList<>();
//        for (Employee employee : employees) {
//            EmployeeResponse employeeResponse = new EmployeeResponse();
//            //map employee to employee respone
//
//            employeeResponse.setEmpEmail(employee.getEmpEmail());
//            employeeResponse.setEmpName(employee.getEmpName());
//            employeeResponse.setEmpSurname(employee.getEmpSurname());
////            employeeResponse.setDepartment(employee.getDepartment());
//            employeesResponse.add(employeeResponse);
        return employeeRepo.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public EmployeeResponse getEmployeeById (Integer id) {
        return employeeRepo.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Empolyee not found with id: " + id));
    }
    public EmployeeResponse save(EmployeeRequest employeeRequest){
//        // map employeeRequest to employee
//        Employee employee = new Employee();
//        employee.setEmpName(employeeRequest.getEmpName());
//        employee.setEmpEmail(employeeRequest.getEmpEmail());
//        employee.setEmpSurname(employeeRequest.getEmpSurname());
//        employee.setEmpPhone(employeeRequest.getEmpPhone());
//        employee.setDepartment(employeeRequest.getDepartment());
//
//        Employee employee1  =   employeeRepo.save(employee);
//        //map employee to response
//         EmployeeResponse employeeResponse = new EmployeeResponse();
//         employeeResponse.setEmpEmail(employee1.getEmpEmail());
//         employeeResponse.setEmpSurname(employee1.getEmpSurname());
//         employeeResponse.setEmpPhone(employee1.getEmpPhone());
//         employeeResponse.setEmpName(employee1.getEmpName());
//         employeeResponse.setDepartment(employee1.getDepartment());
//         return employeeResponse;
        Department department =departmentRepo.findById(employeeRequest.getDepartment().getId())
                .orElseThrow(()->new RuntimeException("Department not found" ));

        List<Project> projects = new ArrayList<>();
        for(Integer id : employeeRequest.getProjectId()){
            Project project = projectRepo.findById(id).orElse(null);
            if(project != null){
                projects.add(project);
            }
        }

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
//        return employeeRepo.findById(id).orElse(null);
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
