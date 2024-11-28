package com.sm.technical_test.service;

import com.sm.technical_test.entity.FileUpload;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileUploadService {
    FileUpload uploadFile(MultipartFile file, String projectId) throws IOException;
}
