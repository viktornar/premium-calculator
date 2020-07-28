package com.github.viktornar.premium.calculator;

import com.github.viktornar.types.RiskType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CalculatorFactory {
    private final Map<RiskType, GenericCalculator> calculators = new HashMap<RiskType, GenericCalculator>() {
        {
            put(RiskType.FRAUD, new GenericCalculator(new FraudCalculator()));
            put(RiskType.THEFT, new GenericCalculator(new TheftCalculator()));
        }
    };

    public GenericCalculator getCalculator(RiskType riskType) throws NotFoundCalculatorException {
        GenericCalculator genericCalculator = this.calculators.get(riskType);

        if (genericCalculator == null) {
            throw new NotFoundCalculatorException("Not found calculator for specified risk type.");
        }

        return genericCalculator;
    }
}
