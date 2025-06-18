package com.suzume.sipd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface Repository<T, ID> extends JpaRepository<T, ID> {

    List<T> findAll(Specification<T> specification, Sort sort);

    Page<T> findAll(Specification<T> specification, Pageable pageable);

}
