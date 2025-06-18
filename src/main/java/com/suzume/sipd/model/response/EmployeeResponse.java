package com.suzume.sipd.model.response;

import com.suzume.sipd.entity.MEmployee;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeResponse {
    private Long id;
    private String name;

    public static EmployeeResponse build(MEmployee employee) {
        if (employee == null) return null;
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .build();
    }
}
