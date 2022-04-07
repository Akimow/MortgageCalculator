package org.bank.model;

import java.math.BigDecimal;

public interface Function {
    BigDecimal calculate(Rate rate);
}
