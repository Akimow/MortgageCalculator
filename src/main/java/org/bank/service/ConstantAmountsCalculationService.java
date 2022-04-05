package org.bank.service;

import org.bank.model.InputData;
import org.bank.model.Overpayment;
import org.bank.model.Rate;
import org.bank.model.RateAmounts;

public interface ConstantAmountsCalculationService {

      RateAmounts calculateRate(InputData inputData, Overpayment overpayment);
      RateAmounts calculateRate(InputData inputData, Overpayment overpayment, Rate previousRate) ;


}
