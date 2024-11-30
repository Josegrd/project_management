package com.sm.technical_test.service.impl;

import com.sm.technical_test.entity.FileUpload;
import com.sm.technical_test.entity.Project;
import com.sm.technical_test.repository.FileUploadRepository;
import com.sm.technical_test.repository.ProjectRepository;
import com.sm.technical_test.service.FileUploadService;
import com.sm.technical_test.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
    private final FileUploadRepository fileUploadRepository;
    private final ProjectRepository projectRepository;

    @Value("${file.upload-dir.absolute}")
    private String absoluteUploadDir;

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB
    private static final long MAX_VIDEO_SIZE = 50 * 1024 * 1024; // 50 MB
    private static final String[] ALLOWED_IMAGE_EXTENSIONS = {"png", "jpg", "jpeg"};
    private static final String[] ALLOWED_VIDEO_EXTENSIONS = {"mp4", "mov", "avi"};
    private static final String[] ALLOWED_DOCUMENT_EXTENSIONS = {"pdf", "docx"};

    @Override
    public FileUpload uploadFiles(String projectId, MultipartFile pdfFile, MultipartFile videoFile, MultipartFile imageFile) throws IOException {
        // Cek apakah proyek ada
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        FileUpload projectFile = new FileUpload();
        projectFile.setProject(project);

        if (imageFile != null && !imageFile.isEmpty()) {
            validateFile(imageFile, ALLOWED_IMAGE_EXTENSIONS, "Image", MAX_FILE_SIZE);
            String imageFilePath = saveFile(imageFile);
            projectFile.setImageFilePath(imageFilePath);
        }

        if (videoFile != null && !videoFile.isEmpty()) {
            validateFile(videoFile, ALLOWED_VIDEO_EXTENSIONS, "Video", MAX_VIDEO_SIZE); // Maks 50 MB
            String videoFilePath = saveFile(videoFile);
            projectFile.setVideoFilePath(videoFilePath);
        }

        if (pdfFile != null && !pdfFile.isEmpty()) {
            validateFile(pdfFile, ALLOWED_DOCUMENT_EXTENSIONS, "Document", MAX_FILE_SIZE);
            String pdfFilePath = saveFile(pdfFile);
            projectFile.setPdfFilePath(pdfFilePath);
        }

        return fileUploadRepository.save(projectFile);
    }

    @Override
    public FileUpload updateFiles(String projectId, MultipartFile pdfFile, MultipartFile videoFile, MultipartFile imageFile) throws IOException {
        // Cari file berdasarkan projectId
        FileUpload existingFile = fileUploadRepository.findByProjectId(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("No files found for the given project ID"));

        // Update PDF file jika diberikan
        if (pdfFile != null && !pdfFile.isEmpty()) {
            validateFile(pdfFile, ALLOWED_DOCUMENT_EXTENSIONS, "Document", MAX_FILE_SIZE);
            String pdfFilePath = saveFile(pdfFile);

            // Hapus file lama jika ada
            if (existingFile.getPdfFilePath() != null) {
                deleteFile(existingFile.getPdfFilePath());
            }

            // Simpan file baru
            existingFile.setPdfFilePath(pdfFilePath);
        }

        // Update video file jika diberikan
        if (videoFile != null && !videoFile.isEmpty()) {
            validateFile(videoFile, ALLOWED_VIDEO_EXTENSIONS, "Video", MAX_VIDEO_SIZE);
            String videoFilePath = saveFile(videoFile);

            // Hapus file lama jika ada
            if (existingFile.getVideoFilePath() != null) {
                deleteFile(existingFile.getVideoFilePath());
            }

            // Simpan file baru
            existingFile.setVideoFilePath(videoFilePath);
        }

        // Update image file jika diberikan
        if (imageFile != null && !imageFile.isEmpty()) {
            validateFile(imageFile, ALLOWED_IMAGE_EXTENSIONS, "Image", MAX_FILE_SIZE);
            String imageFilePath = saveFile(imageFile);

            // Hapus file lama jika ada
            if (existingFile.getImageFilePath() != null) {
                deleteFile(existingFile.getImageFilePath());
            }

            // Simpan file baru
            existingFile.setImageFilePath(imageFilePath);
        }

        // Simpan perubahan ke database
        return fileUploadRepository.save(existingFile);
    }


    private void validateFile(MultipartFile file, String[] allowedExtensions, String fileType, long maxSize) {
        // Validasi ukuran file
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException(fileType + " file size must be less than " + (maxSize / (1024 * 1024)) + " MB");
        }

        // Validasi ekstensi file
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if (extension == null || !isAllowedExtension(extension, allowedExtensions)) {
            throw new IllegalArgumentException("Invalid " + fileType + " file extension. Allowed: " + String.join(", ", allowedExtensions));
        }
    }

    private void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }


    private boolean isAllowedExtension(String extension, String[] allowedExtensions) {
        for (String allowed : allowedExtensions) {
            if (allowed.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    private String saveFile(MultipartFile file) throws IOException {
        // Create the directory if it doesn't exist
        File directory = new File(absoluteUploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = absoluteUploadDir + file.getOriginalFilename();
        File destinationFile = new File(filePath);
        file.transferTo(destinationFile);
        return filePath;
    }
}
