package com.suzume.sipd.service;

import com.suzume.sipd.model.response.DownloadFileResponse;
import com.suzume.sipd.model.response.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    UploadFileResponse upload(MultipartFile file, String directory);

    DownloadFileResponse download(String fileName, String directory);

    void delete(String fileName, String directory);

}
