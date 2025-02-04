package com.example.Ems.project;

import com.example.Ems.Employee.EmployeeRequest;
import com.example.Ems.Employee.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/project")
@RequiredArgsConstructor
public class ProjectController {
    private final  ProjectService projectService;


    @PostMapping
    public ResponseEntity<?> saveProject(@RequestBody ProjectRequest projectRequest ){
        return projectService.save(projectRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Integer id) {
        return projectService.findById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable  Integer id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}")
    public  ResponseEntity<ProjectResponse>updateProject (@PathVariable Integer id , @RequestBody ProjectRequest request)
    {
        return ResponseEntity.ok( projectService.update(id ,request));
    }
}
