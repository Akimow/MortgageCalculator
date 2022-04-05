package org.bank.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputData {

    @With private LocalDate repaymentStartDate = LocalDate.of(2022,1,6);
    @With private BigDecimal wiborPercent = new BigDecimal("4.22");
    @With private BigDecimal amount = BigDecimal.valueOf(300000);
    @With private BigDecimal monthsDuration = new BigDecimal(100);
    @With private RateType rateType = RateType.CONSTANT;
    @With private BigDecimal bankMarginPercent = BigDecimal.valueOf(1.9);

    @With private Map<Integer, BigDecimal> overpaymentSchema = Map.of(
            13, BigDecimal.valueOf(5000),
            25, BigDecimal.valueOf(5000),
            37, BigDecimal.valueOf(5000),
            49, BigDecimal.valueOf(5000),
            61, BigDecimal.valueOf(5000),
            73, BigDecimal.valueOf(5000)
    );

    @With private String overpaymentReduceWay = Overpayment.REDUCE_PERIOD;
    @With private BigDecimal overpaymentProvisionPercent = BigDecimal.valueOf(3);
    @With private BigDecimal overpaymentProvisionMonths = BigDecimal.valueOf(36);




    public BigDecimal getInterestsPercent() {
        return wiborPercent.add(bankMarginPercent).divide(BigDecimal.valueOf(100),10,RoundingMode.HALF_UP);
    }
    public BigDecimal getInterestsDisplay() {
        return wiborPercent.add(bankMarginPercent).setScale(2, RoundingMode.HALF_UP);
    }
}
