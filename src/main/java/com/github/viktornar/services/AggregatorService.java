package com.github.viktornar.services;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.models.CustomerModel;
import com.github.viktornar.premium.aggregator.Aggregator;
import com.github.viktornar.types.RiskType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AggregatorService implements Aggregator {
    @Override
    public Map<RiskType, List<CardModel>> groupCustomerCardsByRiskType(RiskType riskType, CustomerModel customerModel) {
        return null;
    }

    @Override
    public Map<RiskType, List<CardModel>> groupCustomersCardsByRiskType(RiskType riskType, List<CustomerModel> customerModel) {
        return null;
    }

    @Override
    public Map<RiskType, List<CardModel>> mergeGroupedCustomersCards(List<Map<RiskType, List<CardModel>>> groupedCustomerCards) {
        return null;
    }

    @Override
    public Map<RiskType, List<CardModel>> getGroupedCustomersCards(List<CustomerModel> customers) {
        return null;
    }
}
