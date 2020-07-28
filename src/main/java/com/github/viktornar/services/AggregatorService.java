package com.github.viktornar.services;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.models.CustomerModel;
import com.github.viktornar.premium.aggregator.Aggregator;
import com.github.viktornar.types.RiskType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AggregatorService implements Aggregator {
    @Override
    public Map<RiskType, List<CardModel>> groupCustomerCardsByRiskType(CustomerModel customerModel) {
        return customerModel.getCards().stream().collect(Collectors.groupingBy(CardModel::getRiskType));
    }

    @Override
    public Map<RiskType, List<CardModel>> groupCustomersCardsByRiskType(List<CustomerModel> customerModel) {
        List<Map<RiskType, List<CardModel>>> groupedCustomerCards =
                customerModel
                        .stream()
                        .map(this::groupCustomerCardsByRiskType)
                        .collect(Collectors.toList());

        return mergeGroupedCustomersCards(groupedCustomerCards);
    }

    @Override
    public Map<RiskType, List<CardModel>> mergeGroupedCustomersCards(List<Map<RiskType, List<CardModel>>> groupedCustomerCards) {
        return groupedCustomerCards
                .stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (l, r) -> Stream.concat(l.stream(), r.stream()).collect(Collectors.toList())
                        )
                );
    }
}
