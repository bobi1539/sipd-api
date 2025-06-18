package com.suzume.sipd.model.request;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.enums.TransportationMode;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TripSegmentRequest {

    @NotNull(message = Constant.SEQUENCE_REQUIRED)
    private Integer sequenceOrder;

    @NotNull(message = Constant.DEPARTURE_DATE_REQUIRED)
    private LocalDate departureDate;

    @NotNull(message = Constant.TRANSPORTATION_MODE_REQUIRED)
    private TransportationMode transportationMode;

    @NotNull(message = Constant.DEPARTURE_CITY_ID_REQUIRED)
    private Long departureCityId;

    @NotNull(message = Constant.DESTINATION_CITY_ID_REQUIRED)
    private Long destinationCityId;

}
