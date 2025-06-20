package com.suzume.sipd.service.handler.expense.impl;

import com.suzume.sipd.constant.enums.ExpenseType;
import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.TTripExpense;
import com.suzume.sipd.entity.TTripSegment;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.service.handler.AbstractBaseHandler;
import com.suzume.sipd.service.handler.expense.TripExpenseHandler;

import java.util.ArrayList;
import java.util.List;

public class AccommodationFeeHandler extends AbstractBaseHandler implements TripExpenseHandler {

    private String destinationCity;

    @Override
    public List<TTripExpense> handle(MBusinessTrip businessTrip) {
        Header header = getHeaderFromBaseEntity(businessTrip);

        List<TTripExpense> tripExpenses = new ArrayList<>();
        for (int i = 0; i < businessTrip.getTripSegments().size() - 1; i++) {
            TTripSegment tripSegment = businessTrip.getTripSegments().get(i);
            destinationCity = tripSegment.getDestination().getName();

            TTripExpense tripExpense = buildTripExpense(ExpenseType.ACCOMMODATION_FEE);
            setCreatedAndUpdated(tripExpense, header);
            tripExpenses.add(tripExpense);
        }

        return tripExpenses;
    }

    @Override
    public String getDescription() {
        return String.format("Biaya penginapan di %s", destinationCity);
    }

}
