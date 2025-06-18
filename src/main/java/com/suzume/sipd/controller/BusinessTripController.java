package com.suzume.sipd.controller;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.Endpoint;
import com.suzume.sipd.model.response.BusinessTripOptionsResponse;
import com.suzume.sipd.service.BusinessTripService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoint.BUSINESS_TRIP)
@SecurityRequirement(name = Constant.AUTHORIZATION)
public class BusinessTripController {

    private final BusinessTripService businessTripService;

    @GetMapping("/options")
    public BusinessTripOptionsResponse getOptions() {
        return businessTripService.getOptions();
    }

}
