package org.bank.service;

import org.bank.model.InputData;
import org.bank.model.MortgageRef;
import org.bank.model.Rate;
import org.bank.model.RateAmounts;

public interface RefCalcService {
    MortgageRef calculate(InputData inputData);

    MortgageRef calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate);
}
