package com.example.Ems.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {
    private String Name;
    private String Description;
    private Integer id;
}
