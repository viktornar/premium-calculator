package com.github.viktornar.premium.aggregator;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.models.CustomerModel;
import com.github.viktornar.types.RiskType;

import java.util.List;
import java.util.Map;

public interface Aggregator {
    Map<RiskType, List<CardModel>> groupCustomerCardsByRiskType(RiskType riskType, CustomerModel customerModel);
    Map<RiskType, List<CardModel>> mergeGroupedCustomersCards(Map<RiskType, List<CardModel>>[] groupedCustomerCards);
}
