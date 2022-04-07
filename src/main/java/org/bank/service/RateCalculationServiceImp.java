package org.bank.service;

import lombok.AllArgsConstructor;
import org.bank.model.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class RateCalculationServiceImp implements RateCalculationService {

    private final TimePointService timePointService;
    private final AmountsCalculationService amountsCalculationService;
    private final ResidualCalculationService residualCalculationService;
    private final RefCalcService refCalcService;
    private final OverpaymentCalcService overpaymentCalcService;

    @Override
    public List<Rate> calculate(InputData inputData) {

        List<Rate> rates = new LinkedList<>();
        BigDecimal rateNumber = BigDecimal.ONE;

        Rate firstRate = calculateZeroRate(rateNumber, inputData);
        rates.add(firstRate);

        Rate previousRate = firstRate;

        for (BigDecimal index = rateNumber.add(BigDecimal.ONE);
             index.compareTo(inputData.getMonthsDuration()) <= 0;
             index = index.add(BigDecimal.ONE)) {
            Rate nextRate = calculateNextRate(index, inputData, previousRate);
            rates.add(nextRate);
                if(nextRate.getMortgageResidual().getAmount()
                    .compareTo(BigDecimal.ZERO) <= 0){
                break;
            }
            previousRate = nextRate;
        }
        return rates;
    }


    private Rate calculateZeroRate(BigDecimal rateNumber, InputData inputData) {
        TimePoint timePoint = timePointService.caculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalcService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData, overpayment);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, inputData);
        MortgageRef mortgageRef = refCalcService.calculate(inputData);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageRef, overpayment);
    }

    private Rate calculateNextRate(final BigDecimal rateNumber, final InputData inputData, final Rate previousRate) {
        TimePoint timePoint = timePointService.caculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalcService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData, overpayment, previousRate);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, inputData, previousRate);
        MortgageRef mortgageRef = refCalcService.calculate(inputData,rateAmounts,previousRate);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageRef, overpayment);
    }
}
