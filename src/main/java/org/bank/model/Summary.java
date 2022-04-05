package org.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class Summary {
    private final BigDecimal interestsSum;
    private final BigDecimal overpaymentProvisions;
    private final BigDecimal totalLost;
}
