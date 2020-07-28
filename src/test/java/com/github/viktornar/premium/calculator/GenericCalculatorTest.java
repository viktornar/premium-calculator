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

import static org.junit.jupiter.api.Assertions.*;

class GenericCalculatorTest {
    GenericCalculator fraudGenericCalculator;
    GenericCalculator theftGenericCalculator;
    List<CardModel> fraudCards;
    List<CardModel> theftCards;

    @BeforeEach
    void setUp() {
        fraudGenericCalculator = new GenericCalculator(new FraudCalculator());
        theftGenericCalculator = new GenericCalculator(new TheftCalculator());

        fraudCards = Arrays.asList(
                new CardModel(CardType.DEBIT, 30.0, RiskType.FRAUD),
                new CardModel(CardType.DEBIT, 30.0, RiskType.FRAUD),
                new CardModel(CardType.DEBIT, 40.0, RiskType.FRAUD)
        );

        theftCards = Arrays.asList(
                new CardModel(CardType.DEBIT, 2.0, RiskType.THEFT),
                new CardModel(CardType.DEBIT, 1.0, RiskType.THEFT),
                new CardModel(CardType.DEBIT, 5.0, RiskType.THEFT)
        );
    }

    @Test
    void shouldCalculateCorrectPremium() {
        double premium = fraudGenericCalculator.calculate(fraudCards) + theftGenericCalculator.calculate(theftCards);

        Assert.assertEquals(2.28, MathUtil.round(premium, 2), 0.00);
    }
}