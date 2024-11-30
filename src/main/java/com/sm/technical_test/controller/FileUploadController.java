package com.sm.technical_test.controller;

import com.sm.technical_test.entity.FileUpload;
import com.sm.technical_test.model.response.WebResponse;
import com.sm.technical_test.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(
            @RequestParam String projectId,
            @RequestParam(required = false) MultipartFile pdfFile,
            @RequestParam(required = false) MultipartFile videoFile,
            @RequestParam(required = false) MultipartFile imageFile) {

        try {
            FileUpload projectFile = fileUploadService.uploadFiles(projectId, pdfFile, videoFile, imageFile);
            WebResponse<FileUpload> response = WebResponse.<FileUpload>builder()
                    .status("OK")
                    .message("Files uploaded successfully")
                    .data(projectFile)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            WebResponse<String> errorResponse = WebResponse.<String>builder()
                    .status("ERROR")
                    .message(e.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (IOException e) {
            WebResponse<String> errorResponse = WebResponse.<String>builder()
                    .status("ERROR")
                    .message("File processing error: " + e.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}