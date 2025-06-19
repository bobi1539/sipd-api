package com.suzume.sipd.service.handler.attachment.impl;

import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.TTripAttachmentFile;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.request.TripAttachmentFileRequest;
import com.suzume.sipd.service.handler.AbstractBaseHandler;
import com.suzume.sipd.service.handler.attachment.TripAttachmentFileHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripAttachmentFileHandlerImpl extends AbstractBaseHandler implements TripAttachmentFileHandler {

    @Override
    public List<TTripAttachmentFile> create(MBusinessTrip businessTrip, List<TripAttachmentFileRequest> requests) {
        Header header = getHeaderFromBaseEntity(businessTrip);

        return requests.stream().map(request -> {
            TTripAttachmentFile tripAttachmentFile = TTripAttachmentFile.builder().build();
            tripAttachmentFile.setFileName(request.getAttachmentFile());
            tripAttachmentFile.setBusinessTrip(businessTrip);
            setCreatedAndUpdated(tripAttachmentFile, header);
            return tripAttachmentFile;
        }).toList();
    }

}
