package com.github.viktornar.premium.calculator;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.premium.Constants;

import java.util.List;

public class TheftCalculator implements Calculator {
    @Override
    public double calculatePremium(double sum) {
        if (sum >= 15) {
            return sum * Constants.COEFFICIENT_EQUAL_OR_GREATER_15_THEFT;
        }

        return sum * Constants.COEFFICIENT_DEFAULT_THEFT;
    }

    @Override
    public double calculateTotalInsuranceSum(List<CardModel> cards) {
        return cards.stream().mapToDouble(CardModel::getInsuredSum).sum();
    }
}
