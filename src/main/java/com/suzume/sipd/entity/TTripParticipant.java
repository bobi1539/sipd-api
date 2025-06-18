package com.suzume.sipd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "t_trip_participant")
public class TTripParticipant extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "business_trip_id")
    private MBusinessTrip businessTrip;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private MEmployee participant;

    @OneToMany(mappedBy = TTripExpense.F_TRIP_PARTICIPANT, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TTripExpense> tripExpenses = new ArrayList<>();

    public static final String F_BUSINESS_TRIP = "businessTrip";

}
