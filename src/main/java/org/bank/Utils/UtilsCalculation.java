package org.bank.Utils;

import org.bank.model.InputData;
import org.bank.model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UtilsCalculation {

    private UtilsCalculation() {
    }

    public static final BigDecimal YEAR = BigDecimal.valueOf(12);

    public static BigDecimal calculateInterestsAmount(BigDecimal residualAmount, BigDecimal interestsPercent) {
        return residualAmount.multiply(interestsPercent).divide(YEAR, 50, RoundingMode.HALF_DOWN);
    }


    public static BigDecimal getResidualAmount(RateAmounts rateAmounts, InputData inputData) {
        return inputData.getAmount()
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount());
    }

}
