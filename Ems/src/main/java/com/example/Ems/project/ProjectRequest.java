package com.example.Ems.project;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectRequest {
    @NotEmpty(message =  "name should not be empty")
    @NotNull(message = "name should be not null")
    private String Name;
    @NotEmpty(message = "description should not be empty")
    @NotNull( message =  "description should be not null")
    private String Description;
}
