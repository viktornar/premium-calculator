package com.github.viktornar.premium.calculator;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.types.RiskType;

import java.util.List;

public class TheftCalculator implements Calculator {
    @Override
    public double calculatePremium(double sum) {
        return 0;
    }

    @Override
    public double calculateTotalInsuranceSum(List<CardModel> cards) {
        return 0;
    }
}
