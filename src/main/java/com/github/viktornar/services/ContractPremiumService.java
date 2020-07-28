package com.github.viktornar.services;

import com.github.viktornar.models.ContractModel;
import com.github.viktornar.premium.Premium;
import com.github.viktornar.premium.calculator.CalculatorFactory;
import com.github.viktornar.premium.calculator.NotFoundCalculatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractPremiumService implements Premium {
    private AggregatorService aggregatorService;
    private CalculatorFactory calculatorFactory;

    @Autowired
    public ContractPremiumService(AggregatorService aggregatorService, CalculatorFactory calculatorFactory) {
        this.aggregatorService = aggregatorService;
        this.calculatorFactory = calculatorFactory;
    }

    @Override
    public double getContractPremium(ContractModel contract) {
        return 0;
    }
}
