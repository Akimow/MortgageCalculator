package org.bank.service;

import lombok.AllArgsConstructor;
import org.bank.model.InputData;
import org.bank.model.Rate;
import org.bank.model.Summary;

import java.util.List;

@AllArgsConstructor
public class MortgageCalculationServiceImp implements MortgageCalculationService{

    private final PrintingService printingService;
    private final RateCalculationService rateCalculationService;
    private final SummaryService summaryService;


    public void Calculation(InputData inputData){
        List<Rate> rates = rateCalculationService.calculate(inputData);
        Summary summary = summaryService.calculateInterestsSum(rates);

        printingService.printRates(rates);
        printingService.printInputDataInfo(inputData);
        printingService.printSummary(summary);
    }
}
