package com.suzume.sipd.service;

import com.suzume.sipd.entity.AbstractMasterEntity;
import com.suzume.sipd.helper.SpecificationHelper;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.repository.MasterRepository;
import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractMasterService extends AbstractBaseService {

    protected <T extends AbstractMasterEntity, ID> void softDelete(
            MasterRepository<T, ID> repository,
            T entity,
            Header header
    ) {
        entity.setDeleted(true);
        setUpdatedBy(entity, header);
        repository.save(entity);
    }

    protected <T extends AbstractMasterEntity, ID> void restoreData(
            MasterRepository<T, ID> repository,
            T entity,
            Header header
    ) {
        entity.setDeleted(false);
        setUpdatedBy(entity, header);
        repository.save(entity);
    }

    protected void setDeleted(AbstractMasterEntity entity, Header header) {
        setCreatedBy(entity, header);
        setUpdatedBy(entity, header);
        entity.setDeleted(false);
    }

    protected <T> Specification<T> getSpecIsDeleted(Boolean isDeleted) {
        return SpecificationHelper.objectEquals(AbstractMasterEntity.F_IS_DELETED, isDeleted);
    }
}
