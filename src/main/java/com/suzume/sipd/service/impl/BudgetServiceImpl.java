package com.suzume.sipd.service.impl;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.entity.MBudget;
import com.suzume.sipd.helper.SpecificationHelper;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.request.BudgetRequest;
import com.suzume.sipd.model.response.BudgetResponse;
import com.suzume.sipd.repository.BudgetRepository;
import com.suzume.sipd.service.AbstractMasterService;
import com.suzume.sipd.service.BudgetService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BudgetServiceImpl extends AbstractMasterService implements BudgetService {

    private final BudgetRepository budgetRepository;

    @Override
    public Page<BudgetResponse> findAllPagination(Search search, Header header) {
        Specification<MBudget> spec = getSpec(search);
        Pageable pageable = pageableSortByIsDeletedAndIdAsc(search);
        Page<MBudget> budgets = budgetRepository.findAll(spec, pageable);
        return budgets.map(this::toResponse);
    }

    @Override
    public BudgetResponse findById(Long id, Header header) {
        return toResponse(findByIdEntity(id, header));
    }

    @Override
    public BudgetResponse create(BudgetRequest request, Header header) {
        MBudget budget = MBudget.builder().build();
        setBudgetData(budget, request);
        setDeleted(budget, header);
        return toResponse(save(budget));
    }

    @Override
    public BudgetResponse update(Long id, BudgetRequest request, Header header) {
        MBudget budget = findByIdEntity(id, header);
        setBudgetData(budget, request);
        setUpdatedBy(budget, header);
        return toResponse(save(budget));
    }

    @Transactional
    @Override
    public BudgetResponse delete(Long id, Header header) {
        MBudget budget = findByIdEntity(id, header);

        if (budget.isDeleted()) {
            hardDelete(budgetRepository, budget);
            return toResponse(budget);
        }

        softDelete(budgetRepository, budget, header);
        return toResponse(budget);
    }

    @Override
    public BudgetResponse restore(Long id, Header header) {
        MBudget budget = findByIdEntity(id, header);
        restoreData(budgetRepository, budget, header);
        return toResponse(budget);
    }

    @Override
    public MBudget findByIdEntity(Long id, Header header) {
        return budgetRepository.findById(id).orElseThrow(notFoundException(Constant.BUDGET));
    }

    private Specification<MBudget> getSpec(Search search) {
        Specification<MBudget> spec = SpecificationHelper.stringLike(MBudget.F_NAME, search.getValue());
        return spec.and(getSpecIsDeleted(search.getIsDeleted()));
    }

    private BudgetResponse toResponse(MBudget budget) {
        return BudgetResponse.build(budget);
    }

    private void setBudgetData(MBudget budget, BudgetRequest request) {
        budget.setName(request.getName());
        budget.setPrice(request.getPrice());
        budget.setQuantity(request.getQuantity());
    }

    private MBudget save(MBudget budget) {
        return budgetRepository.save(budget);
    }
}
