package com.sm.technical_test.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String fileName;
    private String fileType;
    private String filePath;
    private LocalDateTime uploadDate;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}