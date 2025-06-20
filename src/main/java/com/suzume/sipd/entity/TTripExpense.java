package com.suzume.sipd.entity;

import com.suzume.sipd.constant.enums.ExpenseType;
import com.suzume.sipd.constant.enums.PaymentMethod;
import jakarta.persistence.*;
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
@Entity
@Table(name = "t_trip_expense")
public class TTripExpense extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_type")
    private ExpenseType expenseType;

    @ManyToOne
    @JoinColumn(name = "trip_participant_id")
    private TTripParticipant tripParticipant;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private MBudget budget;

    public static final String F_TRIP_PARTICIPANT = "tripParticipant";
    public static final String F_BUDGET = "budget";

    public BigDecimal getTotal() {
        if (unitPrice == null || quantity == null) return BigDecimal.ZERO;
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public static TTripExpense clone(TTripExpense source, TTripParticipant participant) {
        return TTripExpense.builder()
                .id(source.getId())
                .description(source.getDescription())
                .unitPrice(source.getUnitPrice())
                .quantity(source.getQuantity())
                .paymentMethod(source.getPaymentMethod())
                .expenseType(source.getExpenseType())
                .tripParticipant(participant)
                .budget(source.getBudget())
                .createdAt(source.getCreatedAt())
                .createdBy(source.getCreatedBy())
                .createdByName(source.getCreatedByName())
                .updatedAt(source.getUpdatedAt())
                .updatedBy(source.getUpdatedBy())
                .updatedByName(source.getUpdatedByName())
                .build();
    }
}
