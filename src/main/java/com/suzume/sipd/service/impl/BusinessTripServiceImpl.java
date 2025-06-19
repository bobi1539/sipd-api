package com.suzume.sipd.service.impl;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.enums.*;
import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.helper.PageHelper;
import com.suzume.sipd.helper.SpecificationHelper;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.request.BusinessTripRequest;
import com.suzume.sipd.model.response.*;
import com.suzume.sipd.repository.BusinessTripRepository;
import com.suzume.sipd.service.AbstractMasterService;
import com.suzume.sipd.service.BusinessTripService;
import com.suzume.sipd.service.handler.participant.AbstractTripParticipantHandler;
import com.suzume.sipd.service.handler.participant.TripParticipantHandlerRegistry;
import com.suzume.sipd.service.handler.segment.TripSegmentHandler;
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
    private final TripParticipantHandlerRegistry tripParticipantHandlerRegistry;
    private final TripSegmentHandler tripSegmentHandler;
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
        MBusinessTrip businessTrip = MBusinessTrip.builder().build();
        setDeleted(businessTrip, header);
        businessTrip.setPurpose(request.getPurpose());
        businessTrip.setApprovalFile(request.getApprovalFile());
        businessTrip.setBusinessTripStatus(BusinessTripStatus.CONCEPT);
        businessTrip.setBusinessTripType(request.getBusinessTripType());

        ParticipantType participantType = request.getParticipantType();
        businessTrip.setParticipantType(participantType);

        AbstractTripParticipantHandler tripParticipantHandler = tripParticipantHandlerRegistry.getHandler(participantType);
        businessTrip.setTripParticipants(tripParticipantHandler.handle(businessTrip, request.getTripParticipants()));

        businessTrip.setTripSegments(tripSegmentHandler.create(businessTrip, request.getTripSegments()));

        businessTrip = businessTripRepository.save(businessTrip);

        return toResponse(businessTrip);
    }

    @Override
    public BusinessTripDetailResponse update(Long id, BusinessTripRequest request, Header header) {
        return null;
    }

    @Override
    public BusinessTripDetailResponse delete(Long id, Header header) {
        MBusinessTrip businessTrip = findByIdEntity(id, header);

        if (businessTrip.isDeleted()) {
            hardDelete(businessTripRepository, businessTrip);
            return toResponse(businessTrip);
        }

        softDelete(businessTripRepository, businessTrip, header);
        return toResponse(businessTrip);
    }

    @Override
    public BusinessTripDetailResponse restore(Long id, Header header) {
        MBusinessTrip businessTrip = findByIdEntity(id, header);
        restoreData(businessTripRepository, businessTrip, header);
        return toResponse(businessTrip);
    }

    @Override
    public MBusinessTrip findByIdEntity(Long id, Header header) {
        return businessTripRepository.findById(id).orElseThrow(notFoundException(Constant.BUSINESS_TRIP));
    }

    @Override
    public GetDirectoryResponse getDirectory() {
        return GetDirectoryResponse.builder().directoryName(DIRECTORY).build();
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
