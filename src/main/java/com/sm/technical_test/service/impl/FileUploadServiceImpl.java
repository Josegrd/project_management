package com.sm.technical_test.service.impl;

import com.sm.technical_test.entity.FileUpload;
import com.sm.technical_test.entity.Project;
import com.sm.technical_test.repository.FileUploadRepository;
import com.sm.technical_test.repository.ProjectRepository;
import com.sm.technical_test.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
    private final FileUploadRepository fileUploadRepository;
    private final ProjectRepository projectRepository;

    @Value("${file.upload-dir.relative}")
    private String relativeUploadDir;

    @Value("${file.upload-dir.absolute}")
    private String absoluteUploadDir;

    @Override
    public FileUpload uploadFile(MultipartFile file, String projectId) throws IOException {
        // Cek apakah proyek ada
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Tentukan direktori yang akan digunakan
        File directory = new File(absoluteUploadDir);
        if (!directory.exists()) {
            // Jika direktori absolut tidak ada, coba direktori relatif
            directory = new File(relativeUploadDir);
            if (!directory.exists()) {
                // Jika direktori relatif juga tidak ada, buat direktori
                directory.mkdirs();
            }
        } else {
            // Jika direktori absolut ada, pastikan untuk membuatnya jika belum ada
            directory.mkdirs();
        }

        // Simpan file
        String filePath = directory.getAbsolutePath() + "/" + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        // Simpan informasi file ke database
        FileUpload fileUpload = FileUpload.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath(filePath)
                .uploadDate(LocalDateTime.now())
                .project(project)
                .build();

        return fileUploadRepository.save(fileUpload);
    }
}