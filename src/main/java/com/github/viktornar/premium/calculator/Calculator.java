package com.github.viktornar.premium.calculator;

import com.github.viktornar.models.CardModel;

import java.util.List;

public interface Calculator {
    double calculatePremium(double sum);
    double calculateTotalInsuranceSum(List<CardModel> cards);
}
