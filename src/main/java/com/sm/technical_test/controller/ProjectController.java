package com.sm.technical_test.controller;


import com.sm.technical_test.model.request.ProjectRequest;
import com.sm.technical_test.model.response.ProjectResponse;
import com.sm.technical_test.model.response.WebResponse;
import com.sm.technical_test.service.ProjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<WebResponse<ProjectResponse>> createProject(@RequestBody ProjectRequest projectRequest){
        ProjectResponse projectResponse = projectService.createProject(projectRequest);
        WebResponse<ProjectResponse> response = WebResponse.<ProjectResponse>builder()
                .status("OK")
                .message("Success create new project")
                .data(projectResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebResponse<ProjectResponse>> updateProject(@PathVariable String id, @RequestBody ProjectRequest projectRequest){
        ProjectResponse projectResponse = projectService.updateProject(id, projectRequest);
        WebResponse<ProjectResponse> response = WebResponse.<ProjectResponse>builder()
                .status("OK")
                .message("Success update project")
                .data(projectResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebResponse<ProjectResponse>> getById(@PathVariable String id){
        ProjectResponse projectResponse = projectService.getById(id);
        WebResponse<ProjectResponse> response = WebResponse.<ProjectResponse>builder()
                .status("OK")
                .message("Success get project")
                .data(projectResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<WebResponse<List<ProjectResponse>>> getAllProject(){
        List<ProjectResponse> projectResponse = projectService.getAllProject();
        WebResponse<List<ProjectResponse>> response = WebResponse.<List<ProjectResponse>>builder()
                .status("OK")
                .message("Success get all project")
                .data(projectResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WebResponse<String>> deleteProject(@PathVariable String id){
        projectService.deleteProject(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status("OK")
                .message("Success delete project")
                .data(id)
                .build();
        return ResponseEntity.ok(response);
    }
}
