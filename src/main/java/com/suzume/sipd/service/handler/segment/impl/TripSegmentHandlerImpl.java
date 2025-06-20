package com.suzume.sipd.service.handler.segment.impl;

import com.suzume.sipd.constant.enums.GlobalMessage;
import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.MCity;
import com.suzume.sipd.entity.TTripSegment;
import com.suzume.sipd.exception.BusinessException;
import com.suzume.sipd.helper.ListHelper;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.request.TripSegmentRequest;
import com.suzume.sipd.service.CityService;
import com.suzume.sipd.service.handler.AbstractBaseHandler;
import com.suzume.sipd.service.handler.segment.TripSegmentHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TripSegmentHandlerImpl extends AbstractBaseHandler implements TripSegmentHandler {

    private final CityService cityService;

    @Override
    public List<TTripSegment> create(MBusinessTrip businessTrip, List<TripSegmentRequest> requests) {
        validateRequest(requests);

        Header header = getHeaderFromBaseEntity(businessTrip);

        return requests.stream().map(request -> {
            TTripSegment tripSegment = TTripSegment.builder().build();
            tripSegment.setSequenceOrder(request.getSequenceOrder());
            tripSegment.setDepartureDate(request.getDepartureDate());
            tripSegment.setTransportationMode(request.getTransportationMode());
            tripSegment.setDeparture(getCityById(request.getDepartureCityId(), header));
            tripSegment.setDestination(getCityById(request.getDestinationCityId(), header));
            tripSegment.setBusinessTrip(businessTrip);
            setCreatedAndUpdated(tripSegment, header);
            return tripSegment;
        }).toList();
    }

    private void validateRequest(List<TripSegmentRequest> requests) {
        if (ListHelper.isEmpty(requests) || requests.size() < 2) {
            throw new BusinessException(GlobalMessage.TRIP_SEGMENT_NOT_VALID);
        }

        for (int i = 1; i < requests.size(); i++) {
            TripSegmentRequest departure = requests.get(i - 1);
            TripSegmentRequest destination = requests.get(i);

            if (departure.getDepartureDate().isAfter(destination.getDepartureDate())) {
                throw new BusinessException(GlobalMessage.TRIP_SEGMENT_NOT_VALID);
            }
        }
    }

    private MCity getCityById(Long id, Header header) {
        return cityService.findByIdEntity(id, header);
    }

}
