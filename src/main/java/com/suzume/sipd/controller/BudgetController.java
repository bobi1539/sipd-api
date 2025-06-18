package com.suzume.sipd.controller;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.Endpoint;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.request.BudgetRequest;
import com.suzume.sipd.model.response.BudgetResponse;
import com.suzume.sipd.service.BudgetService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoint.BUDGET)
@SecurityRequirement(name = Constant.AUTHORIZATION)
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping
    public Page<BudgetResponse> findAllPagination(
            @ParameterObject @ModelAttribute Search search,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return budgetService.findAllPagination(search, header);
    }

    @GetMapping("/{id}")
    public BudgetResponse findById(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return budgetService.findById(id, header);
    }

    @PostMapping
    public BudgetResponse create(
            @RequestBody @Valid BudgetRequest request,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return budgetService.create(request, header);
    }

    @PutMapping("/{id}")
    public BudgetResponse update(
            @PathVariable Long id,
            @RequestBody @Valid BudgetRequest request,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return budgetService.update(id, request, header);
    }

    @DeleteMapping("/{id}")
    public BudgetResponse delete(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return budgetService.delete(id, header);
    }

    @PutMapping("/restore/{id}")
    public BudgetResponse restore(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return budgetService.restore(id, header);
    }
}
