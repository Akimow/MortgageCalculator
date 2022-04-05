package org.bank.service;

import org.bank.Utils.UtilsCalculation;
import org.bank.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConstantAmountsCalculationServiceImpl implements ConstantAmountsCalculationService{


    public RateAmounts calculateRate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        BigDecimal interestsPercent = inputData.getInterestsPercent();
        BigDecimal residualAmount = previousRate.getMortgageResidual().getAmount();
        BigDecimal referenceDuration = previousRate.getMortgageRef().getRefDuration();
        BigDecimal referenceAmount = previousRate.getMortgageRef().getRefAmount();

        BigDecimal q = calculateQ(interestsPercent);

        BigDecimal interestsAmount = UtilsCalculation.calculateInterestsAmount(residualAmount, interestsPercent);
        BigDecimal rateAmount = calculateRateAmount(q, referenceAmount, referenceDuration, interestsAmount,  residualAmount);
        BigDecimal capitalAmount = calculateCapitalAmount(rateAmount, interestsAmount, residualAmount);

        return new RateAmounts(rateAmount, interestsAmount, capitalAmount,overpayment);
    }

    public RateAmounts calculateRate(InputData inputData, Overpayment overpayment) {
        BigDecimal interestsPercent = inputData.getInterestsPercent();
        BigDecimal residualAmount = inputData.getAmount();
        BigDecimal referenceAmount = inputData.getAmount();
        BigDecimal referenceDuration = inputData.getMonthsDuration();

        BigDecimal q = calculateQ(interestsPercent);

        BigDecimal interestsAmount = UtilsCalculation.calculateInterestsAmount(residualAmount, interestsPercent);
        BigDecimal rateAmount = calculateRateAmount(q, referenceAmount, referenceDuration, interestsAmount, residualAmount);
        BigDecimal capitalAmount = calculateCapitalAmount(rateAmount, interestsAmount,residualAmount);

        return new RateAmounts(rateAmount, interestsAmount, capitalAmount,overpayment);
    }


    private BigDecimal calculateQ(BigDecimal interestsPercent) {
        return interestsPercent.divide(UtilsCalculation.YEAR, 50, RoundingMode.HALF_DOWN).add(BigDecimal.ONE);
    }


    private BigDecimal calculateCapitalAmount(BigDecimal rateAmount, BigDecimal interestsAmount, BigDecimal residualAmount) {
        BigDecimal capitalAmount = rateAmount.subtract(interestsAmount);
        if (capitalAmount.compareTo(residualAmount)>0) {
            return residualAmount;
        }
        else return capitalAmount;
    }

    private BigDecimal calculateRateAmount(BigDecimal q, BigDecimal referenceAmount, BigDecimal monthsDuration, BigDecimal interestsAmount, BigDecimal residualAmount) {
        BigDecimal rateAmount = referenceAmount
                .multiply(q.pow(monthsDuration.intValue()))
                .multiply(q.subtract(BigDecimal.ONE))
                .divide(q.pow(monthsDuration.intValue()).subtract(BigDecimal.ONE), 50, RoundingMode.HALF_DOWN);
        return compareRateCapitalToResidualLeftCapital(rateAmount,interestsAmount,residualAmount);

    }


    private BigDecimal compareRateCapitalToResidualLeftCapital(BigDecimal rateAmount, BigDecimal interestsAmount, BigDecimal residualAmount) {
        if (rateAmount.subtract(interestsAmount).compareTo(residualAmount)>=0){
            return residualAmount.add(interestsAmount);
        }
        else return rateAmount;
    }

}
