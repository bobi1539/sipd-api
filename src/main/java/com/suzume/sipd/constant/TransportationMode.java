package com.suzume.sipd.constant;

public enum TransportationMode {

    PLANE("Pesawat"),
    TRAIN("Kereta API"),
    PUBLIC_TRANSPORTATION("Kendaraan Umum"),
    PRIVATE_VEHICLE("Kendaraan Pribadi");

    public final String label;

    TransportationMode(String label) {
        this.label = label;
    }
}
