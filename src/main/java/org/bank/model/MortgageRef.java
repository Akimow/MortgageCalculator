package org.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class MortgageRef {
    public final BigDecimal refAmount;
    public final BigDecimal refDuration;
}
