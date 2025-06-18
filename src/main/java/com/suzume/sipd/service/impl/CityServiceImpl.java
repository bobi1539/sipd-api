package com.suzume.sipd.service.impl;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.entity.MCity;
import com.suzume.sipd.helper.SpecificationHelper;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.response.CityResponse;
import com.suzume.sipd.repository.CityRepository;
import com.suzume.sipd.service.AbstractService;
import com.suzume.sipd.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CityServiceImpl extends AbstractService implements CityService {

    private final CityRepository cityRepository;

    @Override
    public Page<CityResponse> findAllPagination(Search search, Header header) {
        Specification<MCity> spec = getSpec(search);
        Pageable pageable = pageableSortByIdAsc(search);
        Page<MCity> cities = cityRepository.findAll(spec, pageable);
        return cities.map(this::toResponse);
    }

    @Override
    public CityResponse findById(Long id, Header header) {
        return toResponse(findByIdEntity(id, header));
    }

    @Override
    public MCity findByIdEntity(Long id, Header header) {
        return cityRepository.findById(id).orElseThrow(notFoundException(Constant.CITY));
    }

    private Specification<MCity> getSpec(Search search) {
        return SpecificationHelper.stringLike(MCity.F_NAME, search.getValue());
    }

    private CityResponse toResponse(MCity city) {
        return CityResponse.build(city);
    }
}
