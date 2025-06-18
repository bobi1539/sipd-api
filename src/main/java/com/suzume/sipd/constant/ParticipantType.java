package com.suzume.sipd.constant;

public enum ParticipantType {

    SOLO("Sendiri"),
    GROUP("Kelompok");

    public final String label;

    ParticipantType(String label) {
        this.label = label;
    }
}
