package com.sm.technical_test.service;

import com.sm.technical_test.entity.FileUpload;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileUploadService {
    FileUpload uploadFiles(String projectId, MultipartFile pdfFile, MultipartFile videoFile, MultipartFile imageFile) throws IOException;
    FileUpload updateFiles(String projectId, MultipartFile pdfFile, MultipartFile videoFile, MultipartFile imageFile) throws IOException;
}
