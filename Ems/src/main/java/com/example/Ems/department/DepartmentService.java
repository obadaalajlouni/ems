package com.example.Ems.department;

import com.example.Ems.Employee.Employee;
import com.example.Ems.Employee.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    public List<DepartmentResponse> departments() {
        List<Department> department1 = departmentRepo.findAll();
        List<DepartmentResponse> departmentResponses = new ArrayList<>();
        for (Department department : department1) {
            DepartmentResponse departmentResponse = new DepartmentResponse();
            //map dapartment to departmentResponse
            departmentResponse.setId(department.getId());
            departmentResponse.setName(department.getName());
            departmentResponses.add(departmentResponse);
            departmentResponse.setDescription(department.getDescription());
        }
        return departmentResponses;
    }

    public Department getDepartmentById(Integer id) {
        return departmentRepo.findById(id).get();
    }

    public DepartmentResponse addDepartment(DepartmentRequest departmentRequest) {
        //map departmenRequest to department
        Department department = new Department();
        department.setName(departmentRequest.getName());
        department.setDescription(departmentRequest.getDescription());

        Department department1 = departmentRepo.save(department);
        //MAP employee to response
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setId(department1.getId());
        departmentResponse.setName(department1.getName());
        departmentResponse.setDescription(departmentRequest.getDescription());

        return departmentResponse;

    }

    public void deleteById(Integer id) {
        departmentRepo.deleteById(id);
    }

    public Department update(Department department) {
        return departmentRepo.save(department);
    }


}
