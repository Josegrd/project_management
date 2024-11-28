package com.sm.technical_test.controller;

import com.sm.technical_test.entity.FileUpload;
import com.sm.technical_test.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @PostMapping("/upload/{projectId}")
    public ResponseEntity<FileUpload> uploadFiles(@RequestParam("files") MultipartFile files,
                                                        @PathVariable String projectId) {
        try {
            FileUpload uploadedFiles = fileUploadService.uploadFile(files, projectId);
            return ResponseEntity.ok(uploadedFiles);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}