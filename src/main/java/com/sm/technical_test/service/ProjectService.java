package com.sm.technical_test.service;

import com.sm.technical_test.model.request.ProjectRequest;
import com.sm.technical_test.model.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse createProject(ProjectRequest projectRequest);
    ProjectResponse updateProject(String id, ProjectRequest projectRequest);
    ProjectResponse getById(String id);
    List<ProjectResponse> getAllProject();
    void deleteProject(String id);

}
