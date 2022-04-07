package org.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@ToString
public class MortgageResidual {
    private final BigDecimal amount;
    private final BigDecimal duration;

    public BigDecimal getAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDuration() {
        return duration;
    }
}
