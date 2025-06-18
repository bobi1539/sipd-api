package com.suzume.sipd.model.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TripAttachmentFileRequest {
    private MultipartFile attachmentFile;
}
