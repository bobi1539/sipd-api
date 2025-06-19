package com.suzume.sipd.service.impl;

import com.suzume.sipd.config.AppConfig;
import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.enums.GlobalMessage;
import com.suzume.sipd.exception.BusinessException;
import com.suzume.sipd.model.response.DownloadFileResponse;
import com.suzume.sipd.model.response.UploadFileResponse;
import com.suzume.sipd.service.AbstractFileService;
import com.suzume.sipd.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FileServiceImpl extends AbstractFileService implements FileService {

    public FileServiceImpl(AppConfig appConfig) {
        super(appConfig);
    }

    @Override
    protected Map<String, List<String>> initSupportedFormat() {
        return Map.of(
                "image/jpeg", List.of("jpg", "jpeg"),
                "image/png", List.of("png"),
                "image/x-png", List.of("png"),
                "image/bmp", List.of("bmp"),
                "image/gif", List.of("gif"),
                "image/webp", List.of("webp"),
                "application/pdf", List.of("pdf"),
                "application/msword", List.of("doc"),
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document", List.of("docx")
        );
    }

    @Override
    protected long initMaxFileSize() {
        return 2L * 1_024L * 1_024L;
    }

    @Override
    public UploadFileResponse upload(MultipartFile file, String directory) {
        String fileName = saveFile(file, directory);
        return UploadFileResponse.builder().fileName(fileName).build();
    }

    @Override
    public DownloadFileResponse download(String fileName, String directory) {
        try {
            Path storagePath = Paths.get(appConfig.getPathFile() + directory);
            Path filePath = storagePath.resolve(fileName).normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new BusinessException(GlobalMessage.FILE_DOESNT_EXIST);
            }

            String contentType = Files.probeContentType(filePath);

            return DownloadFileResponse.builder()
                    .resource(resource)
                    .mediaType(MediaType.parseMediaType(contentType))
                    .build();
        } catch (IOException e) {
            log.error(Constant.ERROR, e.getMessage());
            throw new BusinessException(GlobalMessage.FILE_DOESNT_EXIST);
        }
    }

    @Override
    public void delete(String fileName, String directory) {
        deleteFile(fileName, directory);
    }

}
