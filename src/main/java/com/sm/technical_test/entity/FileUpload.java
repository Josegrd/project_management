package com.sm.technical_test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    private String pdfFilePath;
    private String videoFilePath;
    private String imageFilePath;
}