package com.github.viktornar.premium.calculator;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.types.RiskType;

import java.util.List;

public class FraudCalculator implements Calculator {
    public static final RiskType CALCULATOR_TYPE = RiskType.THEFT;

    @Override
    public double calculatePremium(double sum, double coefficient) {
        return 0;
    }

    @Override
    public double calculateTotalInsuranceSum(List<CardModel> cards) {
        return 0;
    }
}
