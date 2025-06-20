package com.suzume.sipd.service.handler.participant;

import com.suzume.sipd.constant.enums.ExpenseType;
import com.suzume.sipd.constant.enums.ParticipantType;
import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.MEmployee;
import com.suzume.sipd.entity.TTripExpense;
import com.suzume.sipd.entity.TTripParticipant;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.request.BusinessTripRequest;
import com.suzume.sipd.model.request.TripParticipantRequest;
import com.suzume.sipd.service.EmployeeService;
import com.suzume.sipd.service.handler.AbstractBaseHandler;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractTripParticipantHandler extends AbstractBaseHandler {

    protected final EmployeeService employeeService;

    protected AbstractTripParticipantHandler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<TTripParticipant> handle(MBusinessTrip businessTrip, BusinessTripRequest request) {
        validateRequest(request.getTripParticipants());

        Header header = getHeaderFromBaseEntity(businessTrip);
        List<TTripExpense> baseExpenses = generateBaseExpenses(businessTrip);

        return request.getTripParticipants().stream().map(r -> {
            TTripParticipant tripParticipant = TTripParticipant.builder().build();
            tripParticipant.setBusinessTrip(businessTrip);
            tripParticipant.setParticipant(getEmployeeById(r.getEmployeeId(), header));
            setCreatedAndUpdated(tripParticipant, header);

            List<TTripExpense> clonedExpenses = baseExpenses.stream()
                    .map(tripExpense -> TTripExpense.clone(tripExpense, tripParticipant))
                    .toList();
            tripParticipant.setTripExpenses(clonedExpenses);

            return tripParticipant;
        }).toList();
    }

    protected MEmployee getEmployeeById(Long id, Header header) {
        return employeeService.findByIdEntity(id, header);
    }

    protected abstract ParticipantType getType();

    protected abstract void validateRequest(List<TripParticipantRequest> requests);

    private List<TTripExpense> generateBaseExpenses(MBusinessTrip businessTrip) {
        return Arrays.stream(ExpenseType.values())
                .flatMap(type -> type.handler.handle(businessTrip).stream())
                .toList();
    }

}
