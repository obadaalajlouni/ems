package com.example.Ems.project;

import com.example.Ems.Employee.EmployeeRequest;
import com.example.Ems.Employee.EmployeeResponse;
import com.example.Ems.configuration.NotFoundInDatabaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {
    private final  ProjectService projectService;


    @PostMapping
    public ResponseEntity<?> saveProject(@RequestBody ProjectRequest projectRequest ){
        return projectService.save(projectRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Integer id) throws NotFoundInDatabaseException {
        return projectService.findById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable  Integer id) throws NotFoundInDatabaseException {
       return projectService.deleteProject(id);

    }
    @PutMapping("{id}")
    public  ResponseEntity<ProjectResponse>updateProject (@PathVariable Integer id , @RequestBody ProjectRequest request) throws NotFoundInDatabaseException {
        return ResponseEntity.ok( projectService.update(id ,request));
    }


}
