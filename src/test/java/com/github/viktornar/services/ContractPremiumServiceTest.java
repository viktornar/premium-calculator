package com.github.viktornar.services;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.models.ContractModel;
import com.github.viktornar.models.CustomerModel;
import com.github.viktornar.premium.calculator.CalculatorFactory;
import com.github.viktornar.premium.calculator.NotFoundCalculatorException;
import com.github.viktornar.types.CardType;
import com.github.viktornar.types.CostumerType;
import com.github.viktornar.types.RiskType;
import com.github.viktornar.types.StatusType;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContractPremiumServiceTest {
    private ContractModel contract;
    private final AggregatorService aggregatorService = new AggregatorService();
    private final CalculatorFactory calculatorFactory = new CalculatorFactory();
    private ContractPremiumService contractPremiumService;

    @BeforeEach
    void setUp() {
        contractPremiumService = new ContractPremiumService(aggregatorService, calculatorFactory);

        contract = new ContractModel();
        contract.setId("LT2078-5252-55");
        contract.setStatusType(StatusType.APPROVED);
        List<CustomerModel> customers = Collections.singletonList(
                new CustomerModel(
                        "Jonas Jonaitis",
                        CostumerType.FATHER,
                        Arrays.asList(
                                new CardModel(
                                        CardType.DEBIT,
                                        100.00,
                                        RiskType.FRAUD
                                ),
                                new CardModel(
                                        CardType.DEBIT,
                                        8.00,
                                        RiskType.THEFT
                                )
                        )
                )
        );

        contract.setCustomers(customers);
    }

    @Test
    void shouldReturnContractPremium() throws NotFoundCalculatorException, NoCardException, NoCustomerException {
        double premium = contractPremiumService.getContractPremium(contract);
        Assert.assertEquals(2.28, premium, 0.00);
    }
}