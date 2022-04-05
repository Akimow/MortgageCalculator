package org.bank.model;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
public class Overpayment {

    public final BigDecimal amount;
    public final BigDecimal provisionAmount;

    public static final String REDUCE_PERIOD = "REDUCE_PERIOD";
    public static final String REDUCE_RATE = "REDUCE_RATE";

    public BigDecimal getAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getProvisionAmount() {
        return provisionAmount.setScale(2, RoundingMode.HALF_UP);
    }
}
