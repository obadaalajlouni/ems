package com.example.Ems.project;

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
                .Name(Project.getName())
                .Description(Project.getDescription())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<?> findById(Integer id){
       Project project = projectRepo.findById(id).orElse(null);
        if(project == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("project not found");
        }
        ProjectResponse response = ProjectResponse.builder()
                .id(Project.getId())
                .firstName(Project.getName())
                .lastName(Project.getDescription())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
