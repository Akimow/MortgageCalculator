package org.bank.service;

import org.bank.model.InputData;
import org.bank.model.TimePoint;

import java.math.BigDecimal;

public interface TimePointService {

    TimePoint caculate(BigDecimal rateNumber, InputData inputData);
}
