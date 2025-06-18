package com.suzume.sipd.helper;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.GlobalMessage;
import com.suzume.sipd.exception.BusinessException;
import org.springframework.data.jpa.domain.Specification;

public final class SpecificationHelper {

    private SpecificationHelper() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static <T> Specification<T> stringLike(String attribute, String value) {
        return (root, query, cb) -> {
            if (StringHelper.isEmpty(value)) return null;
            return cb.like(cb.lower(root.get(attribute)), StringHelper.queryLike(value.toLowerCase()));
        };
    }

    public static <T> Specification<T> objectEquals(String attribute, Object value) {
        return (root, query, cb) -> {
            if (value == null) return null;
            return cb.equal(root.get(attribute), value);
        };
    }

    public static <T> Specification<T> entityIdEquals(String attribute, Long value) {
        return (root, query, cb) -> {
            if (value == null) return null;
            return cb.equal(root.get(attribute).get(Constant.ID), value);
        };
    }
}
