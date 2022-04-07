package org.bank.service;

import lombok.AllArgsConstructor;
import org.bank.model.*;

@AllArgsConstructor
public class AmountsCalculationServiceImpl implements AmountsCalculationService{


    private final ConstantAmountsCalculationService constantAmountsCalculationService;
    private final DecreasingAmountsCalculationService decreasingAmountsCalculationService;

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculateRate(inputData,overpayment);
            case DECREASING:
                return decreasingAmountsCalculationService.calculateRate(inputData,overpayment);
        }
        throw new RuntimeException("Case not handled");

    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculateRate(inputData, overpayment, previousRate);
            case DECREASING:
                return decreasingAmountsCalculationService.calculateRate(inputData, overpayment, previousRate);

        }
        throw new RuntimeException("Case not handled");
    }

}
