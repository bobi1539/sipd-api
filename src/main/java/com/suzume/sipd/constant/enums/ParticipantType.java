package com.suzume.sipd.constant.enums;

import lombok.Getter;

@Getter
public enum ParticipantType implements LabeledEnum {

    SOLO("Sendiri"),
    GROUP("Kelompok");

    public final String label;

    ParticipantType(String label) {
        this.label = label;
    }
}
