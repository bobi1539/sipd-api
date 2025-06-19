package com.suzume.sipd.service.handler.participant;

import com.suzume.sipd.constant.enums.ParticipantType;
import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.TTripParticipant;
import com.suzume.sipd.model.request.TripParticipantRequest;

import java.util.List;

public interface TripParticipantHandler {

    ParticipantType getType();

    List<TTripParticipant> handle(MBusinessTrip businessTrip, List<TripParticipantRequest> requests);

}
