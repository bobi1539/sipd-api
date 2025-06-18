package com.suzume.sipd.service;

import com.suzume.sipd.constant.GlobalMessage;
import com.suzume.sipd.entity.AbstractMasterEntity;
import com.suzume.sipd.exception.BusinessException;
import com.suzume.sipd.helper.PageHelper;
import com.suzume.sipd.helper.StringHelper;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public abstract class AbstractBaseService {

    protected static final String FIELD_ID = "id";

    protected Sort sortByIdAsc() {
        return PageHelper.sortByColumnAsc(FIELD_ID);
    }

    protected Sort sortByIsDeletedAndIdAsc() {
        Sort sortIsDeleted = PageHelper.sortByColumnAsc(AbstractMasterEntity.F_IS_DELETED);
        Sort sortId = PageHelper.sortByColumnAsc(FIELD_ID);
        return sortIsDeleted.and(sortId);
    }

    protected Pageable pageableSortByIdAsc(Search search) {
        return PageHelper.buildPageRequest(search.getPage(), search.getSize(), sortByIdAsc());
    }

    protected Pageable pageableSortByIsDeletedAndIdAsc(Search search) {
        return PageHelper.buildPageRequest(search.getPage(), search.getSize(), sortByIsDeletedAndIdAsc());
    }

    protected void setCreatedBy(AbstractMasterEntity entity, Header header) {
        entity.setCreatedBy(header.getUserId());
        entity.setCreatedByName(header.getUserFullName());
    }

    protected void setUpdatedBy(AbstractMasterEntity baseEntity, Header header) {
        baseEntity.setUpdatedBy(header.getUserId());
        baseEntity.setUpdatedByName(header.getUserFullName());
    }

    protected Supplier<BusinessException> notFoundException(String data) {
        return () -> new BusinessException(HttpStatus.BAD_REQUEST, StringHelper.notFoundFormat(data));
    }

    protected <T, ID> void hardDelete(JpaRepository<T, ID> repository, T entity) {
        try {
            repository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(GlobalMessage.CANNOT_DELETE_THIS_DATA);
        }
    }

}
