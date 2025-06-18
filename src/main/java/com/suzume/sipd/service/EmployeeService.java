package com.suzume.sipd.service;

import com.suzume.sipd.entity.MEmployee;
import com.suzume.sipd.model.dto.Header;
import com.suzume.sipd.model.dto.Search;
import com.suzume.sipd.model.response.EmployeeResponse;
import org.springframework.data.domain.Page;

public interface EmployeeService {

    Page<EmployeeResponse> findAllPagination(Search search, Header header);

    EmployeeResponse findById(Long id, Header header);

    MEmployee findByIdEntity(Long id, Header header);

}
