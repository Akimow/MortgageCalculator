package org.bank.service;

import org.bank.model.Function;
import org.bank.model.Rate;
import org.bank.model.Summary;

import java.math.BigDecimal;
import java.util.List;

public class SummaryServiceFactory {


    public static SummaryService create() {
        return rateList -> {
            BigDecimal interestSum = calculateSum(rateList, rate -> rate.getRateAmounts().getInterestsAmount() );
            BigDecimal provisions = calculateSum(rateList, rate -> rate.getRateAmounts().getOverpayment().getProvisionAmount());
            BigDecimal totalLost = interestSum.add(provisions);

            return new Summary(interestSum, provisions, totalLost);
        };

    }

    private static BigDecimal calculateSum(List<Rate> rateList, Function function) {
        BigDecimal sum = BigDecimal.ZERO;

        for (Rate rate : rateList) {
            sum= sum.add(function.calculate(rate));
        }
        return sum;
    }
/*
    private static BigDecimal calculateInterestSum(List<Rate> rateList) {
        BigDecimal sum = BigDecimal.ZERO;

        for (Rate rate : rateList) {
            sum= sum.add(rate.getRateAmounts().getInterestsAmount());
        }
        return sum;
    }*/
}
