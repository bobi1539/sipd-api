package com.suzume.sipd.model.response;

import com.suzume.sipd.entity.TTripParticipant;
import com.suzume.sipd.helper.ListHelper;
import lombok.*;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TripParticipantResponse {

    private Long id;
    private EmployeeResponse participant;

    public static TripParticipantResponse build(TTripParticipant tripParticipant) {
        if (tripParticipant == null) return null;
        return TripParticipantResponse.builder()
                .id(tripParticipant.getId())
                .participant(EmployeeResponse.build(tripParticipant.getParticipant()))
                .build();
    }

    public static List<TripParticipantResponse> buildList(List<TTripParticipant> tripParticipants) {
        if (ListHelper.isEmpty(tripParticipants)) return Collections.emptyList();
        return tripParticipants.stream().map(TripParticipantResponse::build).toList();
    }

}
