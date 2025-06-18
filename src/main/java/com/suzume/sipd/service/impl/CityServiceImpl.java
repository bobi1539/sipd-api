package com.suzume.sipd.service.impl;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.entity.MCity;
import com.suzume.sipd.exception.BusinessException;
import com.suzume.sipd.helper.PageHelper;
import com.suzume.sipd.helper.SpecificationHelper;
import com.suzume.sipd.helper.StringHelper;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.response.CityResponse;
import com.suzume.sipd.repository.CityRepository;
import com.suzume.sipd.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public Page<CityResponse> findAllPagination(Search search, Header header) {
        Specification<MCity> spec = getSpec(search);
        Pageable pageable = getPageable(search);
        Page<MCity> cities = cityRepository.findAll(spec, pageable);
        return cities.map(this::toResponse);
    }

    @Override
    public CityResponse findById(Long id, Header header) {
        return toResponse(findByIdEntity(id, header));
    }

    @Override
    public MCity findByIdEntity(Long id, Header header) {
        return cityRepository.findById(id).orElseThrow(() -> new BusinessException(
                HttpStatus.BAD_REQUEST, StringHelper.notFoundFormat(Constant.CITY)
        ));
    }

    private Specification<MCity> getSpec(Search search) {
        return SpecificationHelper.stringLike(MCity.F_NAME, search.getValue());
    }

    private Pageable getPageable(Search search) {
        Sort sort = PageHelper.sortByColumnAsc(MCity.F_ID);
        return PageHelper.buildPageRequest(search.getPage(), search.getSize(), sort);
    }

    private CityResponse toResponse(MCity city) {
        return CityResponse.build(city);
    }
}
