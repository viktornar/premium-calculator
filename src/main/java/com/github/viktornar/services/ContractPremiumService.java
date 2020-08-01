package com.github.viktornar.services;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.models.ContractModel;
import com.github.viktornar.premium.Premium;
import com.github.viktornar.premium.calculator.CalculatorFactory;
import com.github.viktornar.premium.calculator.NotFoundCalculatorException;
import com.github.viktornar.types.RiskType;
import com.github.viktornar.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
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
        log.info("Trying calculate premium for contract with id {}", contract.getId());
        validateContract(contract);
        Map<RiskType, List<CardModel>> groupedCards = this.aggregatorService.groupCustomersCardsByRiskType(contract.getCustomers());
        log.debug("Got grouped cards for contract with id: {}", contract.getId());

        double premium = 0.00;
        for (RiskType riskType: groupedCards.keySet()) {
            List<CardModel> cards = groupedCards.get(riskType);
            premium += calculatorFactory.getCalculator(riskType).calculate(cards);
            log.debug("Premium after applied calculation for card: {}", cards);
        }
        return MathUtil.round(premium, 2);
    }

    private void validateContract(ContractModel contract) throws NoCustomerException {
        if (contract.getCustomers() == null) {
            log.error("Customer was not provided for contract with id: {}", contract.getId());
            throw new NoCustomerException("no.customer.exception");
        }
    }
}
