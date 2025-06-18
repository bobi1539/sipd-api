package com.suzume.sipd.model.request;

import com.suzume.sipd.constant.Constant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BudgetRequest {

    @NotNull(message = Constant.BUDGET_NAME_REQUIRED)
    @NotBlank(message = Constant.BUDGET_NAME_REQUIRED)
    private String name;

    @NotNull(message = Constant.BUDGET_PRICE_REQUIRED)
    private BigDecimal price;

    @NotNull(message = Constant.BUDGET_QUANTITY_REQUIRED)
    private Integer quantity;
}
