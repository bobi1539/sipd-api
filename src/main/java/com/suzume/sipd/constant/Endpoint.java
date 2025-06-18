package com.suzume.sipd.constant;

import com.suzume.sipd.exception.BusinessException;

public final class Endpoint {

    private Endpoint() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static final String BASE = "/api/v1";
    public static final String AUTH = BASE + "/auths";
    public static final String BUDGET = BASE + "/budgets";
}
