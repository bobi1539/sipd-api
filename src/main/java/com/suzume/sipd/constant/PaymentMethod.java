package com.suzume.sipd.constant;

public enum PaymentMethod {

    REIMBURSEMENT("Reimburse"),
    PREPAYMENT("Bayar Dimuka");

    public final String label;

    PaymentMethod(String label) {
        this.label = label;
    }
}
