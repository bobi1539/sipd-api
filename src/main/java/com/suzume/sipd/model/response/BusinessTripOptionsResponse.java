package com.suzume.sipd.model.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusinessTripOptionsResponse {
    private List<OptionResponse> tripTypes;
    private List<OptionResponse> participantTypes;
    private List<OptionResponse> paymentMethods;
    private List<OptionResponse> transportationModes;
}
