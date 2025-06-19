package com.suzume.sipd.service.handler.participant.impl;

import com.suzume.sipd.constant.enums.GlobalMessage;
import com.suzume.sipd.constant.enums.ParticipantType;
import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.MEmployee;
import com.suzume.sipd.entity.TTripParticipant;
import com.suzume.sipd.exception.BusinessException;
import com.suzume.sipd.helper.ListHelper;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.request.TripParticipantRequest;
import com.suzume.sipd.service.EmployeeService;
import com.suzume.sipd.service.handler.AbstractBaseHandler;
import com.suzume.sipd.service.handler.participant.TripParticipantHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SoloTripParticipantHandler extends AbstractBaseHandler implements TripParticipantHandler {

    private final EmployeeService employeeService;

    @Override
    public ParticipantType getType() {
        return ParticipantType.SOLO;
    }

    @Override
    public List<TTripParticipant> handle(MBusinessTrip businessTrip, List<TripParticipantRequest> requests) {
        validateRequest(requests);
        Header header = getHeaderFromBaseEntity(businessTrip);

        return requests.stream().map(r -> {
            TTripParticipant tripParticipant = TTripParticipant.builder().build();
            tripParticipant.setBusinessTrip(businessTrip);
            tripParticipant.setParticipant(getEmployeeById(r.getEmployeeId(), header));
            setCreatedAndUpdated(tripParticipant, header);
            return tripParticipant;
        }).toList();
    }

    private void validateRequest(List<TripParticipantRequest> requests) {
        if (ListHelper.isEmpty(requests) || requests.size() > 1) {
            throw new BusinessException(GlobalMessage.TRIP_PARTICIPANT_NOT_VALID);
        }
    }

    private MEmployee getEmployeeById(Long id, Header header) {
        return employeeService.findByIdEntity(id, header);
    }
}
