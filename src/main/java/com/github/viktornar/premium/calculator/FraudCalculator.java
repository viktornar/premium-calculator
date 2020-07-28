package com.github.viktornar.premium.calculator;

import com.github.viktornar.models.CardModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.github.viktornar.premium.Constants.COEFFICIENT_DEFAULT_FRAUD;
import static com.github.viktornar.premium.Constants.COEFFICIENT_OVER_100_FRAUD;

@Slf4j
public class FraudCalculator implements Calculator {
    @Override
    public double calculatePremium(double sum) {
        if (sum > 100) {
            log.debug("Sum is over 100. {} coefficient will be applied", COEFFICIENT_OVER_100_FRAUD);
            return sum * COEFFICIENT_OVER_100_FRAUD;
        }

        log.debug("{} coefficient will be applied", COEFFICIENT_DEFAULT_FRAUD);
        return sum * COEFFICIENT_DEFAULT_FRAUD;
    }

    @Override
    public double calculateTotalInsuranceSum(List<CardModel> cards) {
        return cards.stream().mapToDouble(CardModel::getInsuredSum).sum();
    }
}
