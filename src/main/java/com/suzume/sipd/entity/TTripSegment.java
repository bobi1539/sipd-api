package com.suzume.sipd.entity;

import com.suzume.sipd.constant.TransportationMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "t_trip_segment")
public class TTripSegment extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sequence_order")
    private Integer sequenceOrder;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "transportation_mode")
    private TransportationMode transportationMode;

    @ManyToOne
    @JoinColumn(name = "departure_id")
    private MCity departure;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private MCity destination;

    @ManyToOne
    @JoinColumn(name = "business_trip_id")
    private MBusinessTrip businessTrip;

    public static final String F_BUSINESS_TRIP = "businessTrip";

}
