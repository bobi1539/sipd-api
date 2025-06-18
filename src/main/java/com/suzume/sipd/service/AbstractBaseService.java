package com.suzume.sipd.service;

import com.suzume.sipd.entity.AbstractMasterEntity;
import com.suzume.sipd.model.dto.Header;

public abstract class AbstractBaseService extends AbstractService {

    protected void setCreatedBy(AbstractMasterEntity entity, Header header) {
        entity.setCreatedBy(header.getUserId());
        entity.setCreatedByName(header.getUserFullName());
    }

    protected void setUpdatedBy(AbstractMasterEntity baseEntity, Header header) {
        baseEntity.setUpdatedBy(header.getUserId());
        baseEntity.setUpdatedByName(header.getUserFullName());
    }

}
