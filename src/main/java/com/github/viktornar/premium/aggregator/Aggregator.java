package com.github.viktornar.premium.aggregator;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.models.CustomerModel;
import com.github.viktornar.services.NoCardException;
import com.github.viktornar.types.RiskType;

import java.util.List;
import java.util.Map;

public interface Aggregator {
    Map<RiskType, List<CardModel>> groupCustomerCardsByRiskType(CustomerModel customerModel) throws NoCardException;

    Map<RiskType, List<CardModel>> groupCustomersCardsByRiskType(List<CustomerModel> customerModel) throws NoCardException;

    Map<RiskType, List<CardModel>> mergeGroupedCustomersCards(List<Map<RiskType, List<CardModel>>> groupedCustomerCards);
}
