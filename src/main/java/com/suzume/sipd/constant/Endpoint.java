package com.suzume.sipd.constant;

import com.suzume.sipd.constant.enums.GlobalMessage;
import com.suzume.sipd.exception.BusinessException;

public final class Endpoint {

    private Endpoint() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static final String BASE = "/api/v1";
    public static final String AUTH = BASE + "/auths";
    public static final String BUDGET = BASE + "/budgets";
    public static final String CITY = BASE + "/cities";
    public static final String EMPLOYEE = BASE + "/employees";
    public static final String BUSINESS_TRIP = BASE + "/business-trips";
    public static final String FILE = BASE + "/files";
}
