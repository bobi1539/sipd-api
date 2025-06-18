package com.suzume.sipd.model.request;

import com.suzume.sipd.constant.Constant;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TripParticipantRequest {

    @NotNull(message = Constant.EMPLOYEE_ID_REQUIRED)
    private Long employeeId;

}
