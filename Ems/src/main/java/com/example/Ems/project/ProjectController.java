package com.example.Ems.project;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/project")
@RequiredArgsConstructor
public class ProjectController {
    private final  ProjectService projectService;


    @PostMapping
    public ResponseEntity<?> saveTeacher(@RequestBody ProjectRequest projectRequest ){
        return projectService.save(projectRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Integer id) {
        return projectService.findById(id);
    }
}
