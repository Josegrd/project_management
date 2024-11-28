package com.sm.technical_test.service.impl;

import com.sm.technical_test.constant.Priority;
import com.sm.technical_test.constant.ProjectStatus;
import com.sm.technical_test.entity.Project;
import com.sm.technical_test.model.request.ProjectRequest;
import com.sm.technical_test.model.response.ProjectResponse;
import com.sm.technical_test.repository.ProjectRepository;
import com.sm.technical_test.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest) {
        try {
            Project newProject = Project.builder()
                    .projectName(projectRequest.getProjectName())
                    .description(projectRequest.getDescription())
                    .startDate(projectRequest.getStartDate())
                    .endDate(projectRequest.getEndDate())
                    .status(ProjectStatus.valueOf(projectRequest.getStatus()))
                    .budget(projectRequest.getBudget())
                    .client(projectRequest.getClient())
                    .priority(Priority.valueOf(projectRequest.getPriority()))
                    .projectManager(projectRequest.getProjectManager())
                    .additionalNotes(projectRequest.getAdditionalNotes())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            projectRepository.save(newProject);
            return convertToResponse(newProject);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create project");
        }
    }

    @Override
    public ProjectResponse updateProject(String id, ProjectRequest projectRequest) {
        try {
            Project project = findByIdOrThrowException(id);
            project.setProjectName(projectRequest.getProjectName());
            project.setDescription(projectRequest.getDescription());
            project.setStartDate(projectRequest.getStartDate());
            project.setEndDate(projectRequest.getEndDate());
            project.setStatus(ProjectStatus.valueOf(projectRequest.getStatus()));
            project.setBudget(projectRequest.getBudget());
            project.setClient(projectRequest.getClient());
            project.setPriority(Priority.valueOf(projectRequest.getPriority()));
            project.setProjectManager(projectRequest.getProjectManager());
            project.setAdditionalNotes(projectRequest.getAdditionalNotes());
            project.setUpdatedAt(LocalDateTime.now());
            projectRepository.save(project);
            return convertToResponse(project);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ProjectResponse getById(String id) {
        Project project = findByIdOrThrowException(id);
        return convertToResponse(project);
    }

    @Override
    public List<ProjectResponse> getAllProject() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(this::convertToResponse).toList();
    }

    @Override
    public void deleteProject(String id) {
        Project project = findByIdOrThrowException(id);
        projectRepository.delete(project);
    }

    private Project findByIdOrThrowException(String id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    }

    private ProjectResponse convertToResponse(Project project) {
        return ProjectResponse.builder()
                .projectName(project.getProjectName())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .status(project.getStatus().name())
                .budget(project.getBudget())
                .client(project.getClient())
                .priority(project.getPriority().name())
                .projectManager(project.getProjectManager())
                .additionalNotes(project.getAdditionalNotes())
                .build();
    }
}
