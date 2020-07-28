package com.github.viktornar.premium.calculator;

import com.github.viktornar.models.CardModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.github.viktornar.premium.Constants.COEFFICIENT_DEFAULT_THEFT;
import static com.github.viktornar.premium.Constants.COEFFICIENT_EQUAL_OR_GREATER_15_THEFT;

@Slf4j
public class TheftCalculator implements Calculator {
    @Override
    public double calculatePremium(double sum) {
        if (sum >= 15) {
            log.debug("Sum is greater or equal than / to 15. {} coefficient will be applied", COEFFICIENT_EQUAL_OR_GREATER_15_THEFT);
            return sum * COEFFICIENT_EQUAL_OR_GREATER_15_THEFT;
        }

        log.debug("{} coefficient will be applied", COEFFICIENT_DEFAULT_THEFT);
        return sum * COEFFICIENT_DEFAULT_THEFT;
    }

    @Override
    public double calculateTotalInsuranceSum(List<CardModel> cards) {
        return cards.stream().mapToDouble(CardModel::getInsuredSum).sum();
    }
}
