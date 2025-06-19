package com.suzume.sipd.service.handler.participant;

import com.suzume.sipd.constant.enums.ParticipantType;
import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.MEmployee;
import com.suzume.sipd.entity.TTripParticipant;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.request.TripParticipantRequest;
import com.suzume.sipd.service.EmployeeService;
import com.suzume.sipd.service.handler.AbstractBaseHandler;

import java.util.List;

public abstract class AbstractTripParticipantHandler extends AbstractBaseHandler {

    protected final EmployeeService employeeService;

    protected AbstractTripParticipantHandler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

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

    protected MEmployee getEmployeeById(Long id, Header header) {
        return employeeService.findByIdEntity(id, header);
    }

    protected abstract ParticipantType getType();

    protected abstract void validateRequest(List<TripParticipantRequest> requests);

}
