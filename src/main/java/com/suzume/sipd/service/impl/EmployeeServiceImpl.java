package com.suzume.sipd.service.impl;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.entity.MEmployee;
import com.suzume.sipd.helper.SpecificationHelper;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.response.EmployeeResponse;
import com.suzume.sipd.repository.EmployeeRepository;
import com.suzume.sipd.service.AbstractMasterService;
import com.suzume.sipd.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl extends AbstractMasterService implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Page<EmployeeResponse> findAllPagination(Search search, Header header) {
        Specification<MEmployee> spec = getSpec(search);
        Pageable pageable = pageableSortByIdAsc(search);
        Page<MEmployee> employees = employeeRepository.findAll(spec, pageable);
        return employees.map(this::toResponse);
    }

    @Override
    public EmployeeResponse findById(Long id, Header header) {
        return toResponse(findByIdEntity(id, header));
    }

    @Override
    public MEmployee findByIdEntity(Long id, Header header) {
        return employeeRepository.findById(id).orElseThrow(notFoundException(Constant.EMPLOYEE));
    }

    private Specification<MEmployee> getSpec(Search search) {
        return SpecificationHelper.stringLike(MEmployee.F_NAME, search.getValue());
    }

    private EmployeeResponse toResponse(MEmployee employee) {
        return EmployeeResponse.build(employee);
    }
}
