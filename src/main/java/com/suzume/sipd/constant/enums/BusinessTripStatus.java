package com.suzume.sipd.constant.enums;

public enum BusinessTripStatus {

    CONCEPT("Konsep", "bg-gray-500"),
    CONCEPT_SUBMITTED("Diajukan", "bg-blue-500"),
    IMPLEMENTATION("Pelaksanaan", "bg-yellow-500"),
    IMPLEMENTATION_SUBMITTED("Pelaksanaan", "bg-orange-500"),
    COMPLETED("Selesai", "bg-green-500"),
    REJECTED("Ditolak", "bg-red-500");

    public final String label;
    public final String color;

    BusinessTripStatus(String label, String color) {
        this.label = label;
        this.color = color;
    }

}
