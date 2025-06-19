package com.suzume.sipd.service.handler;

import com.suzume.sipd.entity.AbstractBaseEntity;
import com.suzume.sipd.model.dto.Header;

public abstract class AbstractBaseHandler {

    protected void setCreated(AbstractBaseEntity entity, Header header) {
        entity.setCreatedBy(header.getUserId());
        entity.setCreatedByName(header.getUserFullName());
    }

    protected void setUpdated(AbstractBaseEntity entity, Header header) {
        entity.setUpdatedBy(header.getUserId());
        entity.setUpdatedByName(header.getUserFullName());
    }

    protected void setCreatedAndUpdated(AbstractBaseEntity entity, Header header) {
        setCreated(entity, header);
        setUpdated(entity, header);
    }

    protected Header getHeaderFromBaseEntity(AbstractBaseEntity entity) {
        return Header.builder()
                .userId(entity.getUpdatedBy())
                .userFullName(entity.getUpdatedByName())
                .build();
    }
}
