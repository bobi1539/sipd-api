package com.suzume.sipd.entity;

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
}
