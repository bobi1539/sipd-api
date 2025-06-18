package com.suzume.sipd.model.response;

import com.suzume.sipd.entity.MBusinessTrip;
import com.suzume.sipd.entity.TTripParticipant;
import com.suzume.sipd.entity.TTripSegment;
import com.suzume.sipd.helper.ListHelper;
import com.suzume.sipd.helper.LocalDateHelper;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusinessTripSimpleResponse {

    private Long id;
    private String purpose;
    private String participantName;
    private String tripPeriod;
    private String tripPlace;
    private String status;
    private String statusColor;

    public static BusinessTripSimpleResponse build(MBusinessTrip businessTrip) {
        if (businessTrip == null) return null;
        return BusinessTripSimpleResponse.builder()
                .id(businessTrip.getId())
                .purpose(businessTrip.getPurpose())
                .participantName(getParticipantName(businessTrip.getTripParticipants()))
                .tripPeriod(getTripPeriod(businessTrip.getTripSegments()))
                .tripPlace(getTripPlace(businessTrip.getTripSegments()))
                .status(businessTrip.getBusinessTripStatus().label)
                .statusColor(businessTrip.getBusinessTripStatus().color)
                .build();
    }

    private static String getParticipantName(List<TTripParticipant> participants) {
        if (ListHelper.isEmpty(participants)) return "";

        String firstParticipantName = participants.get(0).getParticipant().getName();
        int size = participants.size();

        if (size == 1) return firstParticipantName;

        return String.format("%s dan %d orang lainnya", firstParticipantName, size - 1);
    }

    private static String getTripPeriod(List<TTripSegment> tripSegments) {
        if (ListHelper.isEmpty(tripSegments)) return "";

        LocalDate startDate = tripSegments.get(0).getDepartureDate();
        LocalDate endDate = tripSegments.get(tripSegments.size() - 1).getDepartureDate();

        if (startDate.equals(endDate)) return LocalDateHelper.toDateMonthStr(startDate);

        return String.format(
                "%s - %s",
                LocalDateHelper.toDateMonthStr(startDate),
                LocalDateHelper.toDateMonthStr(endDate)
        );
    }

    private static String getTripPlace(List<TTripSegment> tripSegments) {
        if (ListHelper.isEmpty(tripSegments)) return "";

        return tripSegments
                .stream()
                .map(s -> s.getDeparture().getName())
                .collect(Collectors.joining(" - "));
    }
}
