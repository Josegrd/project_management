package com.sm.technical_test.repository;

import com.sm.technical_test.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUpload, String> {
    Optional<FileUpload> findByProjectId(String projectId);
}