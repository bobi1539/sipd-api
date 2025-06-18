package com.suzume.sipd.entity;

import com.suzume.sipd.constant.enums.BusinessTripType;
import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.constant.enums.ParticipantType;
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
@Table(name = "m_business_trip")
public class MBusinessTrip extends AbstractMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "purpose", columnDefinition = Constant.TEXT)
    private String purpose;

    @Column(name = "approval_file")
    private String approvalFile;

    @Enumerated(EnumType.STRING)
    @Column(name = "business_trip_type")
    private BusinessTripType businessTripType;

    @Enumerated(EnumType.STRING)
    @Column(name = "participant_type")
    private ParticipantType participantType;

    @OneToMany(mappedBy = TTripParticipant.F_BUSINESS_TRIP, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TTripParticipant> tripParticipants = new ArrayList<>();

    @OneToMany(mappedBy = TTripSegment.F_BUSINESS_TRIP, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TTripSegment> tripSegments = new ArrayList<>();

    @OneToMany(mappedBy = TTripAttachmentFile.F_BUSINESS_TRIP, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TTripAttachmentFile> attachmentFiles = new ArrayList<>();

}
