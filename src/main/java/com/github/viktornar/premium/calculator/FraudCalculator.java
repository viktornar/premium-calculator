package com.github.viktornar.premium.calculator;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.premium.Constants;

import java.util.List;

public class FraudCalculator implements Calculator {
    @Override
    public double calculatePremium(double sum) {
        if (sum > 100) {
            return sum * Constants.COEFFICIENT_OVER_100_FRAUD;
        }

        return sum * Constants.COEFFICIENT_DEFAULT_FRAUD;
    }

    @Override
    public double calculateTotalInsuranceSum(List<CardModel> cards) {
        return cards.stream().mapToDouble(CardModel::getInsuredSum).sum();
    }
}
