package org.bank.service;

import org.bank.model.InputData;
import org.bank.model.Overpayment;
import org.bank.model.Rate;
import org.bank.model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface AmountsCalculationService {

    public static final BigDecimal YEAR = BigDecimal.valueOf(12);

    RateAmounts calculate(InputData inputData, Overpayment overpayment);
    RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) ;

    static BigDecimal calculateQ(final BigDecimal interestPercent) {
        return interestPercent.divide(AmountsCalculationService.YEAR, 10, RoundingMode.HALF_UP).add(BigDecimal.ONE);
    }

    static BigDecimal compareCapitalWithResidual(final BigDecimal capitalAmount, final BigDecimal residualAmount) {
        if (capitalAmount.compareTo(residualAmount) >= 0) {
            return residualAmount;
        }
        return capitalAmount;
    }
}
