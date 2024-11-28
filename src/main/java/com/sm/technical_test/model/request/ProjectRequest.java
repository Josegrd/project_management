package com.sm.technical_test.model.request;

import com.sm.technical_test.constant.Priority;
import com.sm.technical_test.constant.ProjectStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequest {
    @NotBlank(message = "Project name is required")
    private String projectName;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "Start date is required")
    private LocalDate startDate;
    @NotBlank(message = "End date is required")
    private LocalDate endDate;
    @NotBlank(message = "Status is required")
    private String status;
    @NotBlank(message = "Budget is required")
    private int budget;
    @NotBlank(message = "Client is required")
    private String client;
    @NotBlank(message = "Priority is required")
    private String priority;
    @NotBlank(message = "Project manager is required")
    private String projectManager;
    private String additionalNotes;
}
