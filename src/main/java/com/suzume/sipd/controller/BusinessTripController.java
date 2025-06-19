package com.suzume.sipd.controller;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.Endpoint;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.request.BusinessTripRequest;
import com.suzume.sipd.model.response.BusinessTripDetailResponse;
import com.suzume.sipd.model.response.BusinessTripOptionsResponse;
import com.suzume.sipd.model.response.BusinessTripSimpleResponse;
import com.suzume.sipd.service.BusinessTripService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<BusinessTripSimpleResponse> findAllPagination(
            @ParameterObject @ModelAttribute Search search,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return businessTripService.findAllPagination(search, header);
    }

    @GetMapping("/{id}")
    public BusinessTripDetailResponse findById(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return businessTripService.findById(id, header);
    }

    @PostMapping
    public BusinessTripDetailResponse create(
            @RequestBody @Valid BusinessTripRequest request,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return businessTripService.create(request, header);
    }

    @PutMapping("/{id}")
    public BusinessTripDetailResponse update(
            @PathVariable Long id,
            @RequestBody @Valid BusinessTripRequest request,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return businessTripService.update(id, request, header);
    }

    @DeleteMapping("/{id}")
    public BusinessTripDetailResponse delete(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return businessTripService.delete(id, header);
    }

    @PutMapping("/restore/{id}")
    public BusinessTripDetailResponse restore(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return businessTripService.restore(id, header);
    }

}
