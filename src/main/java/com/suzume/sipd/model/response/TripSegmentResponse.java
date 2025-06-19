package com.suzume.sipd.model.response;

import com.suzume.sipd.constant.enums.TransportationMode;
import com.suzume.sipd.entity.TTripSegment;
import com.suzume.sipd.helper.ListHelper;
import lombok.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TripSegmentResponse {

    private Long id;
    private Integer sequenceOrder;
    private LocalDate departureDate;
    private TransportationMode transportationMode;
    private CityResponse departure;
    private CityResponse destination;

    public static TripSegmentResponse build(TTripSegment tripSegment) {
        if (tripSegment == null) return null;
        return TripSegmentResponse.builder()
                .id(tripSegment.getId())
                .sequenceOrder(tripSegment.getSequenceOrder())
                .departureDate(tripSegment.getDepartureDate())
                .transportationMode(tripSegment.getTransportationMode())
                .departure(CityResponse.build(tripSegment.getDeparture()))
                .destination(CityResponse.build(tripSegment.getDestination()))
                .build();
    }

    public static List<TripSegmentResponse> buildList(List<TTripSegment> tripSegments) {
        if (ListHelper.isEmpty(tripSegments)) return Collections.emptyList();
        return tripSegments.stream().map(TripSegmentResponse::build).toList();
    }

}
