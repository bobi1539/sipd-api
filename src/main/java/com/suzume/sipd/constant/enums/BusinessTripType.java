package com.suzume.sipd.constant.enums;

import lombok.Getter;

@Getter
public enum BusinessTripType implements LabeledEnum {

    DOMESTIC("Perjalanan Dinas Dalam Negeri (PDDN)"),
    ABROAD("Perjalanan Dinas Luar Negeri (PDLN)");

    public final String label;

    BusinessTripType(String label) {
        this.label = label;
    }

}
