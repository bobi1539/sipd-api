package com.suzume.sipd.service.handler.participant.impl;

import com.suzume.sipd.constant.enums.GlobalMessage;
import com.suzume.sipd.constant.enums.ParticipantType;
import com.suzume.sipd.exception.BusinessException;
import com.suzume.sipd.helper.ListHelper;
import com.suzume.sipd.model.request.TripParticipantRequest;
import com.suzume.sipd.service.EmployeeService;
import com.suzume.sipd.service.handler.participant.AbstractTripParticipantHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoloTripParticipantHandler extends AbstractTripParticipantHandler {

    public SoloTripParticipantHandler(EmployeeService employeeService) {
        super(employeeService);
    }

    @Override
    protected ParticipantType getType() {
        return ParticipantType.SOLO;
    }

    @Override
    protected void validateRequest(List<TripParticipantRequest> requests) {
        if (ListHelper.isEmpty(requests) || requests.size() > 1) {
            throw new BusinessException(GlobalMessage.TRIP_PARTICIPANT_NOT_VALID);
        }
    }
}
