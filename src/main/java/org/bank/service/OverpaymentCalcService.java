package org.bank.service;

import org.bank.model.InputData;
import org.bank.model.Overpayment;

import java.math.BigDecimal;

public interface OverpaymentCalcService {
    Overpayment calculate(BigDecimal rateNumber, InputData inputData);
}
