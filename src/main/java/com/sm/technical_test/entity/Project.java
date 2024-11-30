package com.sm.technical_test.entity;


import com.sm.technical_test.constant.Priority;
import com.sm.technical_test.constant.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String projectName;
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;
    private int budget;
    private String client;
    private Priority priority;
    private String projectManager;
    @Column(name = "additional_notes", columnDefinition = "TEXT")
    private String additionalNotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileUpload> fileUploads;
}
