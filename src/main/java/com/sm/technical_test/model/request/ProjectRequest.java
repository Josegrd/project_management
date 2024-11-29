package com.sm.technical_test.model.request;

import com.sm.technical_test.constant.Priority;
import com.sm.technical_test.constant.ProjectStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
    @Future(message = "Start date should take place in future")
    @NotBlank(message = "Start date is required")
    private LocalDate startDate;
    @Future(message = "Finish date should take place in future")
    @NotBlank(message = "End date is required")
    private LocalDate endDate;
    @NotBlank(message = "Status is required")
    private String status;
    @Positive(message = "Price should have positive value")
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
