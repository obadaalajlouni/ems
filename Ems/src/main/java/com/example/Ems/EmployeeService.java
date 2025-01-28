package com.example.Ems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    public String employeeService (){
        return "Employee Service";
    }
    public List<Employee> employeeList() {
        return employeeRepo.findAll();
    }
    public Employee save(Employee employee){
        return   employeeRepo.save(employee );
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
