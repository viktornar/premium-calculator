package com.github.viktornar.premium.calculator;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.premium.Constants;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TheftCalculator implements Calculator {
    @Override
    public double calculatePremium(double sum) {
        if (sum >= 15) {
            log.debug("Sum is greater or equal than / to 15. {} coefficient will be applied", Constants.COEFFICIENT_EQUAL_OR_GREATER_15_THEFT);
            return sum * Constants.COEFFICIENT_EQUAL_OR_GREATER_15_THEFT;
        }

        log.debug("{} coefficient will be applied", Constants.COEFFICIENT_DEFAULT_THEFT);
        return sum * Constants.COEFFICIENT_DEFAULT_THEFT;
    }

    @Override
    public double calculateTotalInsuranceSum(List<CardModel> cards) {
        return cards.stream().mapToDouble(CardModel::getInsuredSum).sum();
    }
}
