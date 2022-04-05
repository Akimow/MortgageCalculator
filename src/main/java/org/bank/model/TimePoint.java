package org.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
public class TimePoint {
    private final LocalDate date;
    private final BigDecimal year;
    private final BigDecimal month;

}
