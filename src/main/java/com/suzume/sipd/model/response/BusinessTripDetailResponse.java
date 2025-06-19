package com.suzume.sipd.model.response;

import com.suzume.sipd.constant.enums.BusinessTripStatus;
import com.suzume.sipd.constant.enums.BusinessTripType;
import com.suzume.sipd.constant.enums.ParticipantType;
import com.suzume.sipd.entity.MBusinessTrip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BusinessTripDetailResponse extends AbstractMasterEntityResponse {

    private Long id;
    private String purpose;
    private String approvalFile;
    private BusinessTripStatus businessTripStatus;
    private BusinessTripType businessTripType;
    private ParticipantType participantType;
    private List<TripParticipantResponse> tripParticipants;
    private List<TripSegmentResponse> tripSegments;
    private List<TripAttachmentFileResponse> tripAttachmentFiles;

    public static BusinessTripDetailResponse build(MBusinessTrip businessTrip) {
        if (businessTrip == null) return null;
        BusinessTripDetailResponse response = BusinessTripDetailResponse.builder()
                .id(businessTrip.getId())
                .purpose(businessTrip.getPurpose())
                .businessTripStatus(businessTrip.getBusinessTripStatus())
                .businessTripType(businessTrip.getBusinessTripType())
                .participantType(businessTrip.getParticipantType())
                .tripParticipants(TripParticipantResponse.buildList(businessTrip.getTripParticipants()))
                .tripSegments(TripSegmentResponse.buildList(businessTrip.getTripSegments()))
                .tripAttachmentFiles(TripAttachmentFileResponse.buildList(businessTrip.getAttachmentFiles()))
                .build();
        setMasterEntity(response, businessTrip);
        return response;
    }

}
