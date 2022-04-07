package org.bank.service;

import org.bank.model.Rate;
import org.bank.model.Summary;

import java.util.List;

public interface SummaryService {

    Summary calculateInterestsSum(List<Rate>rateList);

}
