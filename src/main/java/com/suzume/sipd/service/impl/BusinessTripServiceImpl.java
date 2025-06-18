package com.suzume.sipd.service.impl;

import com.suzume.sipd.constant.enums.BusinessTripType;
import com.suzume.sipd.constant.enums.ParticipantType;
import com.suzume.sipd.constant.enums.PaymentMethod;
import com.suzume.sipd.constant.enums.TransportationMode;
import com.suzume.sipd.model.response.BusinessTripOptionsResponse;
import com.suzume.sipd.model.response.OptionResponse;
import com.suzume.sipd.repository.BusinessTripRepository;
import com.suzume.sipd.service.AbstractMasterService;
import com.suzume.sipd.service.BusinessTripService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BusinessTripServiceImpl extends AbstractMasterService implements BusinessTripService {

    private final BusinessTripRepository businessTripRepository;

    @Override
    public BusinessTripOptionsResponse getOptions() {
        return BusinessTripOptionsResponse.builder()
                .tripTypes(OptionResponse.toOptions(BusinessTripType.class))
                .participantTypes(OptionResponse.toOptions(ParticipantType.class))
                .paymentMethods(OptionResponse.toOptions(PaymentMethod.class))
                .transportationModes(OptionResponse.toOptions(TransportationMode.class))
                .build();
    }
}
