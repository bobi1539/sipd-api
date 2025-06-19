package com.suzume.sipd.service.handler.attachment;

import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.TTripAttachmentFile;
import com.suzume.sipd.model.request.TripAttachmentFileRequest;

import java.util.List;

public interface TripAttachmentFileHandler {

    List<TTripAttachmentFile> create(MBusinessTrip businessTrip, List<TripAttachmentFileRequest> requests);

}
