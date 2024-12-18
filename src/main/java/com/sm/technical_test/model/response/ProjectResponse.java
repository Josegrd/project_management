package com.sm.technical_test.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private String ProjectId;
    private String projectName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private int budget;
    private String client;
    private String priority;
    private String projectManager;
    private String additionalNotes;
    private LocalDateTime ProjectCreateDate;
    private LocalDateTime ProjectModifiedDate;
}
