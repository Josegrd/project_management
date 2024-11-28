package com.sm.technical_test.repository;

import com.sm.technical_test.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileUploadRepository extends JpaRepository<FileUpload, String> {
    List<FileUpload> findByProjectId(String projectId); // Menemukan file berdasarkan ID proyek
}