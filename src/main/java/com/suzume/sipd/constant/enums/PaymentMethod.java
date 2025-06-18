package com.suzume.sipd.constant.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod implements LabeledEnum {

    REIMBURSEMENT("Reimburse"),
    PREPAYMENT("Bayar Dimuka");

    public final String label;

    PaymentMethod(String label) {
        this.label = label;
    }
}
