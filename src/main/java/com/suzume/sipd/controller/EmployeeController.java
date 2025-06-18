package com.suzume.sipd.controller;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.Endpoint;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.response.EmployeeResponse;
import com.suzume.sipd.service.EmployeeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoint.EMPLOYEE)
@SecurityRequirement(name = Constant.AUTHORIZATION)
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public Page<EmployeeResponse> findAllPagination(
            @ParameterObject @ModelAttribute Search search,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return employeeService.findAllPagination(search, header);
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return employeeService.findById(id, header);
    }
}
