package com.github.viktornar.premium.calculator;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.types.CardType;
import com.github.viktornar.types.RiskType;
import com.github.viktornar.utils.MathUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class CalculatorsTest {
    private List<CardModel> cards;
    private FraudCalculator fraudCalculator;
    private TheftCalculator theftCalculator;

    @BeforeEach
    void setUp() {
        cards = Arrays.asList(
                new CardModel(CardType.DEBIT, 20.0, RiskType.FRAUD),
                new CardModel(CardType.DEBIT, 30.0, RiskType.FRAUD),
                new CardModel(CardType.DEBIT, 40.0, RiskType.FRAUD)
        );

        fraudCalculator = new FraudCalculator();
        theftCalculator = new TheftCalculator();
    }

    @Test
    void shouldReturnCorrectPremium() {
        double premiumFraudA = fraudCalculator.calculatePremium(100.0);
        double premiumTheftA = theftCalculator.calculatePremium(8.0);
        double premiumA = premiumFraudA + premiumTheftA;

        Assert.assertEquals(2.28, MathUtil.round(premiumA, 2), 0.00);

        double premiumFraudB = fraudCalculator.calculatePremium(500.00);
        double premiumTheftB = theftCalculator.calculatePremium(102.51);
        double premiumB = premiumFraudB + premiumTheftB;

        Assert.assertEquals(17.13, MathUtil.round(premiumB, 2), 0.00);
    }

    @Test
    void shouldReturnCorrectTotalInsuranceSum() {
        double sum = fraudCalculator.calculateTotalInsuranceSum(cards);
        Assert.assertEquals(90.0, sum, 0.0);
    }
}