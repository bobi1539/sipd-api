package com.suzume.sipd.service.handler.expense.impl;

import com.suzume.sipd.constant.enums.ExpenseType;
import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.TTripExpense;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.service.handler.AbstractBaseHandler;
import com.suzume.sipd.service.handler.expense.TripExpenseHandler;

import java.util.List;

public class TicketFeeHandler extends AbstractBaseHandler implements TripExpenseHandler {

    private String departureCity;
    private String destinationCity;

    @Override
    public List<TTripExpense> handle(MBusinessTrip businessTrip) {
        Header header = getHeaderFromBaseEntity(businessTrip);

        return businessTrip.getTripSegments().stream().map(tripSegment -> {
            departureCity = tripSegment.getDeparture().getName();
            destinationCity = tripSegment.getDestination().getName();

            TTripExpense tripExpense = buildTripExpense(ExpenseType.TICKET_FEE);
            setCreatedAndUpdated(tripExpense, header);
            return tripExpense;
        }).toList();
    }

    @Override
    public String getDescription() {
        return String.format("Biaya tiket dari %s ke %s", departureCity, destinationCity);
    }
}
