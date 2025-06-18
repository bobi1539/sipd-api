package com.suzume.sipd.model.response;

import com.suzume.sipd.entity.MBudget;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BudgetResponse extends AbstractMasterEntityResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal total;

    public static BudgetResponse build(MBudget budget) {
        if (budget == null) return null;
        BudgetResponse response = BudgetResponse.builder()
                .id(budget.getId())
                .name(budget.getName())
                .price(budget.getPrice())
                .quantity(budget.getQuantity())
                .total(budget.getTotal())
                .build();
        setMasterEntity(response, budget);
        return response;
    }

}
