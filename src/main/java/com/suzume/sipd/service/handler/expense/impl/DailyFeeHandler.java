package com.suzume.sipd.service.handler.expense.impl;

import com.suzume.sipd.constant.enums.ExpenseType;
import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.TTripExpense;
import com.suzume.sipd.entity.TTripSegment;
import com.suzume.sipd.helper.LocalDateHelper;
import com.suzume.sipd.service.handler.AbstractBaseHandler;
import com.suzume.sipd.service.handler.expense.TripExpenseHandler;

import java.time.LocalDate;
import java.util.List;

public class DailyFeeHandler extends AbstractBaseHandler implements TripExpenseHandler {

    private List<TTripSegment> tripSegments;

    @Override
    public List<TTripExpense> handle(MBusinessTrip businessTrip) {
        this.tripSegments = businessTrip.getTripSegments();

        TTripExpense tripExpense = buildTripExpense(ExpenseType.DAILY_FEE);
        setCreatedAndUpdated(tripExpense, getHeaderFromBaseEntity(businessTrip));

        return List.of(tripExpense);
    }

    @Override
    public String getDescription() {
        LocalDate startDate = tripSegments.get(0).getDepartureDate();
        LocalDate endDate = tripSegments.get(tripSegments.size() - 1).getDepartureDate();

        return String.format(
                "Uang harian tanggal %s sampai %s",
                LocalDateHelper.todMMMMYYYYFormat(startDate),
                LocalDateHelper.todMMMMYYYYFormat(endDate)
        );
    }

}
