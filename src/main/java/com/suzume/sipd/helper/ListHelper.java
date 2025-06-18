package com.suzume.sipd.helper;

import com.suzume.sipd.constant.enums.GlobalMessage;
import com.suzume.sipd.exception.BusinessException;

import java.util.List;

public final class ListHelper {

    private ListHelper() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> boolean isPresent(List<T> list) {
        return !isEmpty(list);
    }

}
