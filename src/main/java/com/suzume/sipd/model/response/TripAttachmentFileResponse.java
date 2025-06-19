package com.suzume.sipd.model.response;

import com.suzume.sipd.entity.TTripAttachmentFile;
import com.suzume.sipd.helper.ListHelper;
import lombok.*;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TripAttachmentFileResponse {

    private Long id;
    private String fileName;

    public static TripAttachmentFileResponse build(TTripAttachmentFile tripAttachmentFile) {
        if (tripAttachmentFile == null) return null;
        return TripAttachmentFileResponse.builder()
                .id(tripAttachmentFile.getId())
                .fileName(tripAttachmentFile.getFileName())
                .build();
    }

    public static List<TripAttachmentFileResponse> buildList(List<TTripAttachmentFile> tripAttachmentFiles) {
        if (ListHelper.isEmpty(tripAttachmentFiles)) return Collections.emptyList();
        return tripAttachmentFiles.stream().map(TripAttachmentFileResponse::build).toList();
    }

}
