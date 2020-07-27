package com.github.viktornar.premium.calculator;

import com.github.viktornar.models.CardModel;

import java.util.List;

public class GenericCalculator {
    Calculator calculator;

    public GenericCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public double calculate(List<CardModel> cards) {
        double totalInsuranceSum = calculator.calculateTotalInsuranceSum(cards);
        return calculator.calculatePremium(totalInsuranceSum);
    }
}
