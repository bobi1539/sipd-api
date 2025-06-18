package com.suzume.sipd.repository;

import com.suzume.sipd.entity.AbstractMasterEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MasterRepository<T extends AbstractMasterEntity, ID> extends Repository<T, ID> {

}
