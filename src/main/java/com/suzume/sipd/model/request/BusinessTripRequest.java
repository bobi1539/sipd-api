package com.suzume.sipd.model.request;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.enums.BusinessTripType;
import com.suzume.sipd.constant.enums.ParticipantType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusinessTripRequest {

    @NotBlank(message = Constant.TRIP_PURPOSE_REQUIRED)
    @NotNull(message = Constant.TRIP_PURPOSE_REQUIRED)
    private String purpose;

    @NotBlank(message = Constant.TRIP_APPROVAL_FILE_REQUIRED)
    @NotNull(message = Constant.TRIP_APPROVAL_FILE_REQUIRED)
    private String approvalFile;

    @NotNull(message = Constant.TRIP_TYPE_REQUIRED)
    private BusinessTripType businessTripType;

    @NotNull(message = Constant.TRIP_PARTICIPANT_TYPE_REQUIRED)
    private ParticipantType participantType;

    @NotEmpty(message = Constant.TRIP_PARTICIPANT_REQUIRED)
    @NotNull(message = Constant.TRIP_PARTICIPANT_REQUIRED)
    private List<TripParticipantRequest> tripParticipants;

    @NotEmpty(message = Constant.TRIP_SEGMENT_REQUIRED)
    @NotNull(message = Constant.TRIP_SEGMENT_REQUIRED)
    private List<TripSegmentRequest> tripSegments;

    private List<TripAttachmentFileRequest> tripAttachmentFiles;

}
