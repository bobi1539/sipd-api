package com.suzume.sipd.service;

import com.suzume.sipd.entity.MCity;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.response.CityResponse;
import org.springframework.data.domain.Page;

public interface CityService {

    Page<CityResponse> findAllPagination(Search search, Header header);

    CityResponse findById(Long id, Header header);

    MCity findByIdEntity(Long id, Header header);

}
