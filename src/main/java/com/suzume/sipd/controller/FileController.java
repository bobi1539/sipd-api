package com.suzume.sipd.controller;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.Endpoint;
import com.suzume.sipd.model.response.DownloadFileResponse;
import com.suzume.sipd.model.response.UploadFileResponse;
import com.suzume.sipd.service.FileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoint.FILE)
@SecurityRequirement(name = Constant.AUTHORIZATION)
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadFileResponse upload(
            @RequestParam MultipartFile file,
            @RequestParam String directory
    ) {
        return fileService.upload(file, directory);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(
            @PathVariable String fileName,
            @RequestParam String directory
    ) {
        DownloadFileResponse response = fileService.download(fileName, directory);

        Resource resource = response.getResource();
        String contentDispositionValue = Constant.CONTENT_DISPOSITION + resource.getFilename();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDispositionValue)
                .contentType(response.getMediaType())
                .body(resource);
    }

}
