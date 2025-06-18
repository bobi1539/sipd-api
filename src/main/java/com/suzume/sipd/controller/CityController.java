package com.suzume.sipd.controller;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.Endpoint;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.response.CityResponse;
import com.suzume.sipd.service.CityService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoint.CITY)
@SecurityRequirement(name = Constant.AUTHORIZATION)
public class CityController {

    private final CityService cityService;

    @GetMapping
    public Page<CityResponse> findAllPagination(
            @ParameterObject @ModelAttribute Search search,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return cityService.findAllPagination(search, header);
    }

    @GetMapping("/{id}")
    public CityResponse findById(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return cityService.findById(id, header);
    }
}
