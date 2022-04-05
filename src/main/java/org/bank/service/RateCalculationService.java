package org.bank.service;

import org.bank.model.InputData;
import org.bank.model.Rate;

import java.util.List;

public interface RateCalculationService {
     List<Rate> calculate(InputData inputData);
}
