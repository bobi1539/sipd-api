package com.suzume.sipd.service.handler.expense;

import com.suzume.sipd.constant.enums.ExpenseType;
import com.suzume.sipd.constant.enums.PaymentMethod;
import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.TTripExpense;

import java.math.BigDecimal;
import java.util.List;

public interface TripExpenseHandler {

    List<TTripExpense> handle(MBusinessTrip businessTrip);

    String getDescription();

    default TTripExpense buildTripExpense(ExpenseType type) {
        return TTripExpense.builder()
                .description(getDescription())
                .unitPrice(BigDecimal.ZERO)
                .quantity(0)
                .paymentMethod(PaymentMethod.REIMBURSEMENT)
                .expenseType(type)
                .build();
    }

}
