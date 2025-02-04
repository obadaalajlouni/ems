package com.example.Ems.project;

import com.example.Ems.Employee.Employee;
import com.example.Ems.Employee.EmployeeRepo;
import com.example.Ems.Employee.EmployeeRequest;
import com.example.Ems.Employee.EmployeeResponse;
import com.example.Ems.department.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepo projectRepo;

    public ResponseEntity<?> save(ProjectRequest request){
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        projectRepo.save(project);

        ProjectResponse response = ProjectResponse.builder()
                .id(project.getId())
                .Name(project.getName())
                .Description(project.getDescription())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<?> findById(Integer id){
       Project project = projectRepo.findById(id).orElse(null);
        if(project == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("project not found");
        }
        ProjectResponse response = ProjectResponse.builder()
                .id(project.getId())
                .Name(project.getName())
                .Description(project.getDescription())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    public String deleteProject(Integer id){
        projectRepo.deleteById(id);
        return "Project deleted successfully";
    }
    public ProjectResponse update(Integer id, ProjectRequest request) {

        Project projects = projectRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Project not found with id: " ));
       projects.setName(request.getName());
        projects.setDescription(request.getDescription());
        projects =projectRepo.save(projects);
        return  mapToResponse(projects);
    }
    public ProjectResponse mapToResponse(Project projects) {
        ProjectResponse response = ProjectResponse.builder()
                .id(projects.getId())
                .Name(projects.getName())
                .Description(projects.getDescription())
                .build();
        return response;
    }
//
}
