package com.suzume.sipd.service;

import com.suzume.sipd.config.AppConfig;
import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.enums.GlobalMessage;
import com.suzume.sipd.exception.BusinessException;
import com.suzume.sipd.helper.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbstractFileService {

    protected AppConfig appConfig;
    protected Map<String, List<String>> supportedFormat;
    protected long maxFileSize;

    protected AbstractFileService(AppConfig appConfig) {
        this.appConfig = appConfig;
        this.supportedFormat = initSupportedFormat();
        this.maxFileSize = initMaxFileSize();
    }

    protected abstract Map<String, List<String>> initSupportedFormat();

    protected abstract long initMaxFileSize();

    protected String getExtension(String fileName) {
        if (StringHelper.isEmpty(fileName) || !fileName.contains(Constant.DOT)) {
            throw new BusinessException(GlobalMessage.FILE_NOT_VALID);
        }
        return fileName.substring(fileName.lastIndexOf(Constant.DOT) + 1).toLowerCase();
    }

    protected String generateFileName(String extension) {
        return String.format(
                "SIPD_%s_%d.%s",
                StringHelper.random(40),
                System.currentTimeMillis(),
                extension
        );
    }

    protected String saveFile(MultipartFile file, String directory) {
        validateContentType(file);
        validateExtension(file);
        validateSize(file);

        String extension = getExtension(file.getOriginalFilename());
        String fileName = generateFileName(extension);

        Path storagePath = Paths.get(appConfig.getPathFile() + directory).normalize();
        Path filePath = storagePath.resolve(fileName).normalize();

        try {
            Files.createDirectories(storagePath);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            log.info("File saved at : {}", filePath);
            return fileName;
        } catch (IOException e) {
            log.error("Failed to save file : {}", e.getMessage(), e);
            throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
        }
    }

    protected void deleteFile(String fileName, String directory) {
        String directoryPath = appConfig.getPathFile() + directory;
        Path filePath = Paths.get(directoryPath, fileName);

        try {
            Files.deleteIfExists(filePath);
            log.info("Successfully deleted file: {}", filePath);
        } catch (IOException e) {
            log.error("Failed to delete file: {}. Error: {}", filePath, e.getMessage(), e);
        }
    }

    private void validateContentType(MultipartFile file) {
        String contentType = file.getContentType();
        if (StringHelper.isEmpty(contentType) || !supportedFormat.containsKey(contentType)) {
            throw new BusinessException(GlobalMessage.FILE_NOT_ALLOWED);
        }
    }

    private void validateExtension(MultipartFile file) {
        String contentType = file.getContentType();
        List<String> allowedExtensions = supportedFormat.getOrDefault(contentType, List.of());

        String originalExtension = getExtension(file.getOriginalFilename());
        if (!allowedExtensions.contains(originalExtension)) {
            throw new BusinessException(GlobalMessage.FILE_NOT_ALLOWED);
        }
    }

    private void validateSize(MultipartFile file) {
        if (file.getSize() > maxFileSize) {
            String message = String.format("Ukuran File Maksimal %sMB", maxMB());
            throw new BusinessException(HttpStatus.BAD_REQUEST, message);
        }
    }

    private long maxMB() {
        return maxFileSize / (1024 * 1024);
    }

}
