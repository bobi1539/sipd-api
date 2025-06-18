package com.suzume.sipd.service;

import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.response.BusinessTripOptionsResponse;
import com.suzume.sipd.model.response.BusinessTripSimpleResponse;
import org.springframework.data.domain.Page;

public interface BusinessTripService {

    BusinessTripOptionsResponse getOptions();

    Page<BusinessTripSimpleResponse> findAllPagination(Search search, Header header);

}
