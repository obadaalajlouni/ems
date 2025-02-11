package com.example.Ems.department;

import com.example.Ems.Employee.Employee;
import com.example.Ems.Employee.EmployeeResponse;
import com.example.Ems.configuration.NotFoundInDatabaseException;
import com.example.Ems.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?>findById(Integer id) throws NotFoundInDatabaseException {
        Department department = departmentRepo.findById(id).orElseThrow(()-> new NotFoundInDatabaseException("department not found"));
        DepartmentResponse response = mapToResponse(department);
        return ResponseEntity.status(HttpStatus.OK).body(response);
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

    public ResponseEntity<?> deleteById(Integer id)throws NotFoundInDatabaseException {

        Department department =departmentRepo.findById(id).orElseThrow(()-> new NotFoundInDatabaseException("department not found"));
        if(department == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("departmen not found");
        }
        departmentRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity <?>update( Integer id ,DepartmentRequest request )throws NotFoundInDatabaseException {
        Department department = departmentRepo.findById(id).orElseThrow(()-> new NotFoundInDatabaseException("Project not found"));
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department =  departmentRepo.save(department);
        return ResponseEntity.status(HttpStatus.OK).body(department);
    }
    public DepartmentResponse mapToResponse(Department department) {
        return  new DepartmentResponse();
    }


}
