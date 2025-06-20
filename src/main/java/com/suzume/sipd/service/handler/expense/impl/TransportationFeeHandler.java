package com.suzume.sipd.service.handler.expense.impl;

import com.suzume.sipd.constant.enums.ExpenseType;
import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.TTripExpense;
import com.suzume.sipd.service.handler.AbstractBaseHandler;
import com.suzume.sipd.service.handler.expense.TripExpenseHandler;

import java.util.List;

public class TransportationFeeHandler extends AbstractBaseHandler implements TripExpenseHandler {

    @Override
    public List<TTripExpense> handle(MBusinessTrip businessTrip) {
        TTripExpense tripExpense = buildTripExpense(ExpenseType.TRANSPORTATION_FEE);
        setCreatedAndUpdated(tripExpense, getHeaderFromBaseEntity(businessTrip));
        return List.of(tripExpense);
    }

    @Override
    public String getDescription() {
        return "Uang transportasi";
    }
}
