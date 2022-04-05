package org.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@ToString
public class RateAmounts {
    private final BigDecimal rateAmount;
    private final BigDecimal interestsAmount;
    private final BigDecimal capitalAmount;
    private final Overpayment overpayment;

    public BigDecimal getRateAmount() {
        return rateAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestsAmount() {
        return interestsAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getCapitalAmount() {
        return capitalAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public Overpayment getOverpayment() {
        return overpayment;
    }
}
