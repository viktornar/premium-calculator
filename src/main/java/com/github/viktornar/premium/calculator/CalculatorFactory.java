package com.github.viktornar.premium.calculator;

import com.github.viktornar.types.RiskType;

public class CalculatorFactory {
    GenericCalculator getCalculator(RiskType riskType) throws NotFoundCalculatorException {
        if (riskType == RiskType.FRAUD) {
            return new GenericCalculator(new FraudCalculator());
        }

        if (riskType == RiskType.THEFT) {
            return new GenericCalculator(new TheftCalculator());
        }

        throw new NotFoundCalculatorException("Not found calculator for specified risk type.");
    }
}
