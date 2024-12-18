package com.sm.technical_test.repository;

import com.sm.technical_test.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUpload, String> {
    @Query(value = "SELECT * FROM file_upload WHERE project_id = :projectId", nativeQuery = true)
    Optional<FileUpload> findByProjectId(@Param("projectId") String projectId);}