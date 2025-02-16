package com.example.Ems.project;

import com.example.Ems.configuration.NotFoundInDatabaseException;
import com.example.Ems.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepo projectRepo;
    private final ObjectValidator<ProjectRequest> validator;

    public ResponseEntity<?> save(ProjectRequest request){

    validator.validate(request);
        Project project = Project.builder()
                .Name(request.getName())
                .Description(request.getDescription())
                .build();
        projectRepo.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);

    }

    public ResponseEntity<?> findById(Integer id) throws NotFoundInDatabaseException {
        Project project = projectRepo.findById(id).orElseThrow(()-> new NotFoundInDatabaseException("Project not found"));
        ProjectResponse response = mapToResponse(project);
        return ResponseEntity.status(HttpStatus.OK).body(response);


    }
    public ResponseEntity<?> deleteProject(Integer id) throws NotFoundInDatabaseException {
        Project project =projectRepo.findById(id).orElseThrow(()-> new NotFoundInDatabaseException("Project not found"));
                if(project == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
                }
    projectRepo.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    public ProjectResponse update(Integer id, ProjectRequest request)throws NotFoundInDatabaseException {
        Project project = projectRepo.findById(id).orElseThrow(()-> new NotFoundInDatabaseException("Project not found"));
        validator.validate(request);
        project.setName(request.getName());
        project.setDescription(request.getDescription());
       project =  projectRepo.save(project);

        return mapToResponse(project);


    }
    public ProjectResponse mapToResponse(Project projects) {
        ProjectResponse response = ProjectResponse.builder()
                .id(projects.getId())
                .Name(projects.getName())
                .Description(projects.getDescription())
                .build();
        return response;
    }


}
