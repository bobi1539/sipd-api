package com.suzume.sipd.service.handler.segment;

import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.TTripSegment;
import com.suzume.sipd.model.request.TripSegmentRequest;

import java.util.List;

public interface TripSegmentHandler {

    List<TTripSegment> create(MBusinessTrip businessTrip, List<TripSegmentRequest> requests);

}
