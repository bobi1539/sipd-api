package com.suzume.sipd.helper;

import com.suzume.sipd.constant.enums.GlobalMessage;
import com.suzume.sipd.exception.BusinessException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class LocalDateHelper {

    private LocalDateHelper() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static String toDateMonthStr(LocalDate localDate) {
        if (localDate == null) return "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "d MMMM", new Locale("id", "ID")
        );
        return localDate.format(formatter);
    }

}
