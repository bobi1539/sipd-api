package com.suzume.sipd.service.impl;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.enums.BusinessTripType;
import com.suzume.sipd.constant.enums.ParticipantType;
import com.suzume.sipd.constant.enums.PaymentMethod;
import com.suzume.sipd.constant.enums.TransportationMode;
import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.helper.PageHelper;
import com.suzume.sipd.helper.SpecificationHelper;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.request.BusinessTripRequest;
import com.suzume.sipd.model.response.BusinessTripDetailResponse;
import com.suzume.sipd.model.response.BusinessTripOptionsResponse;
import com.suzume.sipd.model.response.BusinessTripSimpleResponse;
import com.suzume.sipd.model.response.OptionResponse;
import com.suzume.sipd.repository.BusinessTripRepository;
import com.suzume.sipd.service.AbstractMasterService;
import com.suzume.sipd.service.BusinessTripService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BusinessTripServiceImpl extends AbstractMasterService implements BusinessTripService {

    private final BusinessTripRepository businessTripRepository;
    private static final String DIRECTORY = "business-trip";

    @Override
    public BusinessTripOptionsResponse getOptions() {
        return BusinessTripOptionsResponse.builder()
                .tripTypes(OptionResponse.toOptions(BusinessTripType.class))
                .participantTypes(OptionResponse.toOptions(ParticipantType.class))
                .paymentMethods(OptionResponse.toOptions(PaymentMethod.class))
                .transportationModes(OptionResponse.toOptions(TransportationMode.class))
                .build();
    }

    @Override
    public Page<BusinessTripSimpleResponse> findAllPagination(Search search, Header header) {
        Specification<MBusinessTrip> spec = getSpec(search);
        Pageable pageable = getPageable(search);
        Page<MBusinessTrip> businessTrips = businessTripRepository.findAll(spec, pageable);
        return businessTrips.map(BusinessTripSimpleResponse::build);
    }

    @Override
    public BusinessTripDetailResponse findById(Long id, Header header) {
        return toResponse(findByIdEntity(id, header));
    }

    @Override
    public BusinessTripDetailResponse create(BusinessTripRequest request, Header header) {
        return null;
    }

    @Override
    public BusinessTripDetailResponse update(Long id, BusinessTripRequest request, Header header) {
        return null;
    }

    @Override
    public BusinessTripDetailResponse delete(Long id, Header header) {
        return null;
    }

    @Override
    public BusinessTripDetailResponse restore(Long id, Header header) {
        return null;
    }

    @Override
    public MBusinessTrip findByIdEntity(Long id, Header header) {
        return businessTripRepository.findById(id).orElseThrow(notFoundException(Constant.BUSINESS_TRIP));
    }

    @Override
    public String getDirectory() {
        return DIRECTORY;
    }

    private Specification<MBusinessTrip> getSpec(Search search) {
        Specification<MBusinessTrip> spec = SpecificationHelper.stringLike(MBusinessTrip.F_PURPOSE, search.getValue());
        return spec.and(getSpecIsDeleted(search.getIsDeleted()));
    }

    private Pageable getPageable(Search search) {
        return PageHelper.buildPageRequest(search.getPage(), search.getSize(), getSort());
    }

    private static Sort getSort() {
        Sort sortIsDeleted = PageHelper.sortByColumnAsc(MBusinessTrip.F_IS_DELETED);
        Sort sortId = PageHelper.sortByColumnDesc(FIELD_ID);
        return sortIsDeleted.and(sortId);
    }

    private BusinessTripDetailResponse toResponse(MBusinessTrip businessTrip) {
        return BusinessTripDetailResponse.build(businessTrip);
    }
}
