package com.suzume.sipd.service;

import com.suzume.sipd.entity.MBudget;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.request.BudgetRequest;
import com.suzume.sipd.model.response.BudgetResponse;
import org.springframework.data.domain.Page;

public interface BudgetService {

    Page<BudgetResponse> findAllPagination(Search search, Header header);

    BudgetResponse findById(Long id, Header header);

    BudgetResponse create(BudgetRequest request, Header header);

    BudgetResponse update(Long id, BudgetRequest request, Header header);

    BudgetResponse delete(Long id, Header header);

    BudgetResponse restore(Long id, Header header);

    MBudget findByIdEntity(Long id, Header header);

}
