package com.suzume.sipd.service;

import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.request.BusinessTripRequest;
import com.suzume.sipd.model.response.BusinessTripDetailResponse;
import com.suzume.sipd.model.response.BusinessTripOptionsResponse;
import com.suzume.sipd.model.response.BusinessTripSimpleResponse;
import org.springframework.data.domain.Page;

public interface BusinessTripService {

    BusinessTripOptionsResponse getOptions();

    Page<BusinessTripSimpleResponse> findAllPagination(Search search, Header header);

    BusinessTripDetailResponse findById(Long id, Header header);

    BusinessTripDetailResponse create(BusinessTripRequest request, Header header);

    BusinessTripDetailResponse update(Long id, BusinessTripRequest request, Header header);

    BusinessTripDetailResponse delete(Long id, Header header);

    BusinessTripDetailResponse restore(Long id, Header header);

    MBusinessTrip findByIdEntity(Long id, Header header);

    String getDirectory();

}
