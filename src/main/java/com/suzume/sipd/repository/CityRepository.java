package com.suzume.sipd.repository;

import com.suzume.sipd.entity.MCity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<MCity, Long> {

    Page<MCity> findAll(Specification<MCity> spec, Pageable pageable);

}
