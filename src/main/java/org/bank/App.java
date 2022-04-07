package org.bank;

import org.bank.model.*;
import org.bank.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class App
{
    public static void main( String[] args )
    {


        Map<Integer, BigDecimal> overpaymentSchema = new TreeMap<>();
        overpaymentSchema.put(5, BigDecimal.valueOf(12000));
        overpaymentSchema.put(19, BigDecimal.valueOf(10000));
        overpaymentSchema.put(28, BigDecimal.valueOf(11000));
        overpaymentSchema.put(64, BigDecimal.valueOf(16000));
        overpaymentSchema.put(78, BigDecimal.valueOf(18000));

        InputData inputData = new InputData()
                .withAmount(BigDecimal.valueOf(500000))
                .withBankMarginPercent(BigDecimal.valueOf(2))
                .withMonthsDuration(BigDecimal.valueOf(240))
                .withRateType(RateType.CONSTANT)
                .withOverpaymentReduceWay(Overpayment.REDUCE_RATE)
//                .withOverpaymentSchema(Collections.emptyMap())
                .withOverpaymentSchema(overpaymentSchema)
                .withRepaymentStartDate((LocalDate.of(2022,4,1)));

        PrintingService printingService = new PrintingServiceImp();
        RateCalculationService rateCalculationService = new RateCalculationServiceImp(
                new TimePointServiceImp(),
                new AmountsCalculationServiceImpl(
                        new ConstantAmountsCalculationServiceImpl(),
                        new DecreasingAmountsCalculationServiceImpl()),
                new ResidualCalculationServiceImp(),
                new RefCalcServiceImpl(),
                new OverpaymentCalcServiceImpl()
        );

        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImp(
                printingService,
                rateCalculationService,
                SummaryServiceFactory.create());

        mortgageCalculationService.Calculation(inputData);


    }
}

