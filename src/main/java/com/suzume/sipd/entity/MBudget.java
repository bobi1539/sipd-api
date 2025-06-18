package com.suzume.sipd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "m_budget")
public class MBudget extends AbstractMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToMany(mappedBy = TTripExpense.F_BUDGET)
    private List<TTripExpense> tripExpenses = new ArrayList<>();

    public static final String F_NAME = "name";

    public BigDecimal getTotal() {
        if (price == null || quantity == null) return BigDecimal.ZERO;
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getUsed() {
        return tripExpenses.stream()
                .map(TTripExpense::getTotal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getRemaining() {
        return getTotal().subtract(getUsed());
    }
}
