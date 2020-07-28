package com.github.viktornar.services;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.models.CustomerModel;
import com.github.viktornar.types.CardType;
import com.github.viktornar.types.CustomerType;
import com.github.viktornar.types.RiskType;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class AggregatorServiceTest {
    private List<CustomerModel> customers;
    private final AggregatorService aggregatorService = new AggregatorService();

    @BeforeEach
    void setUp() {
        customers = Arrays.asList(
                new CustomerModel(
                        "Father",
                        CustomerType.FATHER,
                        Collections.singletonList(
                                new CardModel(
                                        CardType.DEBIT,
                                        1000.00,
                                        RiskType.FRAUD
                                )
                        )
                ),
                new CustomerModel(
                        "Child",
                        CustomerType.CHILD,
                        Arrays.asList(
                                new CardModel(
                                        CardType.DEBIT,
                                        1000.00,
                                        RiskType.FRAUD
                                ),
                                new CardModel(
                                        CardType.DEBIT,
                                        1000.00,
                                        RiskType.THEFT
                                )
                        )
                ),
                new CustomerModel(
                        "Mother",
                        CustomerType.MOTHER,
                        Collections.singletonList(
                                new CardModel(
                                        CardType.DEBIT,
                                        1000.00,
                                        RiskType.FRAUD
                                )
                        )
                )
        );
    }

    @Test
    void shouldGroupCustomersCardsByRiskType() throws NoCardException {
        Map<RiskType, List<CardModel>> grouped = aggregatorService.groupCustomersCardsByRiskType(customers);

        Assert.assertEquals(3, grouped.get(RiskType.FRAUD).size());
        Assert.assertEquals(1, grouped.get(RiskType.THEFT).size());
    }
}