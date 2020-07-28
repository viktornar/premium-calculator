package com.github.viktornar.premium.calculator;

import com.github.viktornar.models.CardModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GenericCalculator {
    Calculator calculator;

    public GenericCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public double calculate(List<CardModel> cards) {
        double totalInsuranceSum = calculator.calculateTotalInsuranceSum(cards);
        log.debug("Total insurence sum is {} for cards: {}", totalInsuranceSum, cards);
        return calculator.calculatePremium(totalInsuranceSum);
    }
}
