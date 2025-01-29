package com.example.Ems.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    public String employeeService (){
        return "Employee Service";
    }
    public List<EmployeeResponse> employeeList() {
        List<Employee> employees= employeeRepo.findAll();
        List<EmployeeResponse> employeesResponse = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeResponse employeeResponse = new EmployeeResponse();
            //map employee to employee respone
            employeeResponse.setEmpEmail( employee.getEmpEmail());
            employeeResponse.setEmpName(employee.getEmpName());
            employeeResponse.setEmpSurname(employee.getEmpSurname());
            employeeResponse.setDepartment(employee.getDepartment());
            employeesResponse.add(employeeResponse);

        }
        return  employeesResponse;
    }
    public EmployeeResponse save(EmployeeRequest employeeRequest){
        // map employeeRequest to employee
        Employee employee = new Employee();
        employee.setEmpName(employeeRequest.getEmpName());
        employee.setEmpEmail(employeeRequest.getEmpEmail());
        employee.setEmpSurname(employeeRequest.getEmpSurname());
        employee.setEmpPhone(employeeRequest.getEmpPhone());
        employee.setDepartment(employeeRequest.getDepartment());

        Employee employee1  =   employeeRepo.save(employee);
        //map employee to response
         EmployeeResponse employeeResponse = new EmployeeResponse();
         employeeResponse.setEmpEmail(employee1.getEmpEmail());
         employeeResponse.setEmpSurname(employee1.getEmpSurname());
         employeeResponse.setEmpPhone(employee1.getEmpPhone());
         employeeResponse.setEmpName(employee1.getEmpName());
         employeeResponse.setDepartment(employee1.getDepartment());
         return employeeResponse;
    }

    public Employee findById(Integer id){
        return employeeRepo.findById(id).orElse(null);
    }
    public void  deleteById(Integer id){
        employeeRepo.deleteById(id);
    }
    public  Employee update(Employee employee){
        return employeeRepo.save(employee);
    }

    public String deleteEmployee(Integer id) {
          employeeRepo.deleteById(id);
        return "Employee deleted successfully";
    }
}
