package com.suzume.sipd.constant.enums;

import com.suzume.sipd.service.handler.expense.TripExpenseHandler;
import com.suzume.sipd.service.handler.expense.impl.AccommodationFeeHandler;
import com.suzume.sipd.service.handler.expense.impl.DailyFeeHandler;
import com.suzume.sipd.service.handler.expense.impl.TicketFeeHandler;
import com.suzume.sipd.service.handler.expense.impl.TransportationFeeHandler;
import lombok.Getter;

@Getter
public enum ExpenseType implements LabeledEnum {

    DAILY_FEE("Uang Harian", new DailyFeeHandler()),
    TRANSPORTATION_FEE("Uang Transportasi", new TransportationFeeHandler()),
    ACCOMMODATION_FEE("Biaya Penginapan", new AccommodationFeeHandler()),
    TICKET_FEE("Biaya Tiket", new TicketFeeHandler());

    public final String label;
    public final TripExpenseHandler handler;

    ExpenseType(String label, TripExpenseHandler handler) {
        this.label = label;
        this.handler = handler;
    }
}
