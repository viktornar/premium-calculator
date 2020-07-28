package com.github.viktornar.services;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.models.ContractModel;
import com.github.viktornar.premium.Premium;
import com.github.viktornar.premium.calculator.CalculatorFactory;
import com.github.viktornar.premium.calculator.NotFoundCalculatorException;
import com.github.viktornar.types.RiskType;
import com.github.viktornar.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContractPremiumService implements Premium {
    private final AggregatorService aggregatorService;
    private final CalculatorFactory calculatorFactory;

    @Autowired
    public ContractPremiumService(AggregatorService aggregatorService, CalculatorFactory calculatorFactory) {
        this.aggregatorService = aggregatorService;
        this.calculatorFactory = calculatorFactory;
    }

    @Override
    public double getContractPremium(ContractModel contract) throws NotFoundCalculatorException, NoCustomerException, NoCardException {
        validateContract(contract);
        Map<RiskType, List<CardModel>> groupedCards = this.aggregatorService.groupCustomersCardsByRiskType(contract.getCustomers());
        double premiumFraud = calculatorFactory.getCalculator(RiskType.FRAUD).calculate(groupedCards.get(RiskType.FRAUD));
        double premiumTheft = calculatorFactory.getCalculator(RiskType.THEFT).calculate(groupedCards.get(RiskType.THEFT));
        return MathUtil.round(premiumFraud + premiumTheft, 2);
    }

    private void validateContract(ContractModel contract) throws NoCustomerException {
        if (contract.getCustomers() == null) {
            throw new NoCustomerException("Please provide at least on customer.");
        }
    }
}
