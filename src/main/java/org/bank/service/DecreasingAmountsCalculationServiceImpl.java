package org.bank.service;

import org.bank.Utils.UtilsCalculation;
import org.bank.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecreasingAmountsCalculationServiceImpl implements DecreasingAmountsCalculationService{

    public RateAmounts calculateRate(final InputData inputData,final Overpayment overpayment) {
        BigDecimal interestsPercent = inputData.getInterestsPercent();
        BigDecimal residualAmount = inputData.getAmount();
        BigDecimal ReferenceDuration = inputData.getMonthsDuration();
        BigDecimal ReferenceAmount = inputData.getAmount();

        BigDecimal interestsAmount = UtilsCalculation.calculateInterestsAmount(residualAmount, interestsPercent);
        BigDecimal capitalAmount = calculateCapitalAmount(ReferenceAmount, ReferenceDuration, residualAmount);
        BigDecimal rateAmount = capitalAmount.add(interestsAmount);

        return new RateAmounts(rateAmount, interestsAmount, capitalAmount,overpayment);
    }

    public RateAmounts calculateRate(final InputData inputData,final Overpayment overpayment,final Rate previousRate) {
        BigDecimal interestsPercent = inputData.getInterestsPercent();
        BigDecimal residualAmount = previousRate.getMortgageResidual().getAmount();
        BigDecimal referenceDuration = previousRate.getMortgageRef().getRefDuration();
        BigDecimal referenceAmount = previousRate.getMortgageRef().getRefAmount();

        BigDecimal interestsAmount = UtilsCalculation.calculateInterestsAmount(residualAmount, interestsPercent);
        BigDecimal capitalAmount = calculateCapitalAmount(referenceAmount, referenceDuration, residualAmount);
        BigDecimal rateAmount = capitalAmount.add(interestsAmount);

        return new RateAmounts(rateAmount, interestsAmount, capitalAmount,overpayment);
    }



    private BigDecimal calculateCapitalAmount( final BigDecimal amount, final BigDecimal monthsDuration,final BigDecimal residualAmount) {
        BigDecimal capitalAmount = amount.divide(monthsDuration, 50, RoundingMode.HALF_UP);
        if (capitalAmount.compareTo(residualAmount)>=0) {
            return residualAmount;
        }
        else return capitalAmount;
    }
}
