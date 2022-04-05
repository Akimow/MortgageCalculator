package org.bank.service;

import org.bank.model.*;

import java.math.BigDecimal;

public class RefCalcServiceImpl implements RefCalcService{

    @Override
    public MortgageRef calculate(InputData inputData) {
        return new MortgageRef(inputData.getAmount(),inputData.getMonthsDuration());
    }

    @Override
    public MortgageRef calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate) {
        if (BigDecimal.ZERO.equals(previousRate.getMortgageResidual().getAmount())){
            return new MortgageRef(BigDecimal.ZERO,BigDecimal.ZERO);
        }
        switch(inputData.getOverpaymentReduceWay()){
            case Overpayment.REDUCE_PERIOD:
                return new MortgageRef(inputData.getAmount(), inputData.getMonthsDuration());
            case Overpayment.REDUCE_RATE:
                return reduceRateMortgageReference(rateAmounts,previousRate);
            default:
                throw new MortgageException("Case not handled");
        }
    }
// tuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu
    private MortgageRef reduceRateMortgageReference(final RateAmounts rateAmounts,final Rate previousRate) {
        if (rateAmounts.getOverpayment().getAmount().compareTo(BigDecimal.ZERO)>0){
            BigDecimal referenceAmount = calculateResidualAmount(rateAmounts, previousRate.getMortgageResidual().getAmount());
            BigDecimal referenceDuration = previousRate.getMortgageResidual().getDuration().subtract(BigDecimal.ONE);

            return new MortgageRef(referenceAmount,referenceDuration);
        }
        else
            return new MortgageRef(previousRate.getMortgageRef().getRefAmount(),previousRate.getMortgageRef().getRefDuration());
    }


    public BigDecimal calculateResidualAmount(RateAmounts rateAmounts, BigDecimal amount) {
        return amount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
