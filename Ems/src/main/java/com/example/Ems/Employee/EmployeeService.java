package com.example.Ems.Employee;

import com.example.Ems.department.Department;
import com.example.Ems.department.DepartmentRepo;
import com.example.Ems.project.Project;
import com.example.Ems.project.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.RequestContextFilter;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final DepartmentRepo departmentRepo;
    private final EmployeeRepo employeeRepo;
    private final RequestContextFilter requestContextFilter;

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
        return employeeRepo.findAll().stream().map(this::mapToResponse).collect(collectors.toList());
    }

    public EmployeeResponse getStudentById(Long id) {
        return EmployeeRepo.findById(id)
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
        Department department =departmentRepo.findById(employeeRequest.getDepartment())
                .orElseThrow(()->new RuntimeException("Department not found" ));

        List<Project> projects = new ArrayList<>();
        for(Integer id : request.getProjectId()){
            Project project = ProjectRepo.findById(id).orElse(null);
            if(project != null){
                projects.add(project);
            }
        }

      Employee employee = Employee.builder()
                .empName(request.getempName())
                .empSurmName(request.getLastName())
                .department(department)
                .project(project)
                .build();

       Employee =EmployeeRepo.save(employee);
        return mapToResponse(employee);
    }

    public Employee findById(Integer id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        employeeRepo.deleteById(id);
    }

    public EmployeeResponse update(Integer id, Employee employee) {
        Employee employee1 = employeeRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Employee not found with id: " ));
        Department department= DepartmentRepo.findByid(request.getDepartment())
                .orElseThrow(()->runTimeException("Department not found"));
        employee.setEmpName(request.getEmpName());
        employee.setEmpPhone(request.getEmpPhone());
        employee.setEmpEmail(request.getEmail());
        employee.setEmpSurname(reqest.getSurname());
         employee =employeeRepo.save(employee);
         return  mapToResponse(employee);
    }

    public String deleteEmployee(Integer id) {
        employeeRepo.deleteById(id);
        return "Employee deleted successfully";
    }
}
