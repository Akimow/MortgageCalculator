package org.bank.service;

import org.bank.model.InputData;
import org.bank.model.Rate;
import org.bank.model.Summary;

import java.util.List;

public interface PrintingService {
    String INTEREST_SUM= "SUMA ODSETEK: ";
    String RATE_NUMBER= "NR: ";
    String MONTHS = " MIESIECY ";
    String MONTH = " MIESIAC";
    String YEAR= "ROK: ";
    String DATE= "DATA: ";
    String RATE= "RATA: ";
    String INTEREST= "ODSETKI: ";
    String INTERESTS= "OPROCENTOWANIE: ";
    String CAPITAL= "KAPITAL: ";
    String LEFT_CAPITAL= "PKAPITAL: ";
    String LEFT_MONTHS= "PMTHS: ";
    String MORTGAGE_AMOUNT= "KWOTA KREDYTU: ";
    String MORTGAGE_PERIOD= "OKRES KREDYTOWANIA: ";
    String OVERPAYMENT_PROVISION= " PROWIZJA ZA NADPLATY: ";
    String OVERPAYMENT_REDUCE_PERIOD= " NADPLATA, SKROCENIE CZASU KREDYTOWANIA";
    String OVERPAYMENT_REDUCE_RTE= " NADPLATA, ZMNIEJSZENIE RATY";
    String OVERPAYMENT_= "SCHEMAT DOKONYWANIA NADPLAT: ";
    String OVERPAYMENT= "KWOTA NADPLATY: ";
    String TOTAL_LOST= "CALKOWITE KOSZTY: ";
    String CURRENCY= " ZL ";
    String NEW_LINE= "\n";
    String PERCENT= "% ";

     void printInputDataInfo(InputData inputData);

    void printRates(List<Rate> rates);

    void printSummary(Summary summary);
}
