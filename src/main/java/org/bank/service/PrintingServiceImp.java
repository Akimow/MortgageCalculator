package org.bank.service;

import org.bank.model.InputData;
import org.bank.model.Overpayment;
import org.bank.model.Rate;
import org.bank.model.Summary;

import java.util.List;
import java.util.Optional;

public class PrintingServiceImp implements PrintingService{

    @Override
    public void printInputDataInfo(InputData inputData) {
        StringBuilder sb = new StringBuilder(NEW_LINE);
        sb.append(MORTGAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        sb.append(NEW_LINE);
        sb.append(MORTGAGE_PERIOD).append(inputData.getMonthsDuration()).append(MONTHS);
        sb.append(NEW_LINE);
        sb.append(INTERESTS).append(inputData.getInterestsDisplay()).append(PERCENT);
        sb.append(NEW_LINE);
        Optional.of(inputData.getOverpaymentSchema())
                .filter(schema -> schema.size() > 0)
                .ifPresent(schema -> logOverpayment(sb, inputData));

        printInfo(sb);

    }

    private void logOverpayment(StringBuilder sb, InputData inputData) {
        switch(inputData.getOverpaymentReduceWay()){
            case Overpayment.REDUCE_PERIOD:
                sb.append(OVERPAYMENT_REDUCE_PERIOD);
                break;
            case Overpayment.REDUCE_RATE:
                sb.append(OVERPAYMENT_REDUCE_RTE);
                break;
            default:
                throw new MortgageException("Case not handled");
        }
        sb.append(NEW_LINE);
        sb.append(OVERPAYMENT_).append(inputData.getOverpaymentSchema());
        sb.append(NEW_LINE);
    }


    @Override
    public void printRates(List<Rate> rates) {
        String format = "%4s %4s" +
                "%4s %4s" +
                "%4s %4s" +
                "%4s %4s" +
                "%4s %4s" +
                "%4s %4s" +
                "%4s %4s" +
                "%4s %8s" +
                "%4s %4s" +
                "%4s %4s" ;

        for (Rate rate : rates) {
            String msg = String.format(format,
                    RATE_NUMBER, rate.getRateNumber(),
                    DATE, rate.getTimePoint().getDate(),
                    YEAR, rate.getTimePoint().getYear(),
                    MONTH, rate.getTimePoint().getMonth(),
                    RATE, rate.getRateAmounts().getRateAmount(),
                    INTEREST, rate.getRateAmounts().getInterestsAmount(),
                    CAPITAL, rate.getRateAmounts().getCapitalAmount(),
                    OVERPAYMENT,rate.getOverpayment().getAmount(),
                    LEFT_CAPITAL, rate.getMortgageResidual().getAmount(),
                    LEFT_MONTHS, rate.getMortgageResidual().getDuration()
                    );
            System.out.println(msg);
            if (rate.getRateNumber().intValue()%12==0){
                System.out.println();
            }

        }
    }

    @Override
    public void printSummary(Summary summary) {
        StringBuilder sb = new StringBuilder(NEW_LINE);
        sb.append(INTEREST_SUM).append(summary.getInterestsSum()).append(CURRENCY);
        sb.append(NEW_LINE);
        sb.append(OVERPAYMENT_PROVISION).append(summary.getOverpaymentProvisions()).append(CURRENCY);
        sb.append(NEW_LINE);
        sb.append(TOTAL_LOST).append(summary.getTotalLost()).append(CURRENCY);
        sb.append(NEW_LINE);
        printInfo(sb);
    }

    public void printInfo(StringBuilder sb){
        System.out.println(sb);
    }
}
