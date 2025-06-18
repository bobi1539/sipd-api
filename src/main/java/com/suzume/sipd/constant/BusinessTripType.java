package com.suzume.sipd.constant;

public enum BusinessTripType {

    DOMESTIC("Perjalanan Dinas Dalam Negeri (PDDN)"),
    ABROAD("Perjalanan Dinas Luar Negeri (PDLN)");

    public final String label;

    BusinessTripType(String label) {
        this.label = label;
    }
}
