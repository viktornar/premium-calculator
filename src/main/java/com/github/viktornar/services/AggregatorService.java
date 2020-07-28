package com.github.viktornar.services;

import com.github.viktornar.models.CardModel;
import com.github.viktornar.models.CustomerModel;
import com.github.viktornar.premium.aggregator.Aggregator;
import com.github.viktornar.types.RiskType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class AggregatorService implements Aggregator {
    @Override
    public Map<RiskType, List<CardModel>> groupCustomerCardsByRiskType(CustomerModel customerModel) throws NoCardException {
        validateCustomer(customerModel);
        return customerModel.getCards().stream().collect(Collectors.groupingBy(CardModel::getRiskType));
    }

    @Override
    public Map<RiskType, List<CardModel>> groupCustomersCardsByRiskType(List<CustomerModel> customers) throws NoCardException {
        log.info("Trying to group cards for customers: {}",  customers);
        List<Map<RiskType, List<CardModel>>> groupedCustomerCards =
                new ArrayList<>();

        for (CustomerModel model : customers) {
            Map<RiskType, List<CardModel>> riskTypeListMap = groupCustomerCardsByRiskType(model);
            log.debug("Customer cards by risk type: {}", riskTypeListMap);
            groupedCustomerCards.add(riskTypeListMap);
        }

        Map<RiskType, List<CardModel>> mergedCards = mergeGroupedCustomersCards(groupedCustomerCards);

        log.info("Grouped customers cards: {}", mergedCards);

        return mergedCards;
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

    private void validateCustomer(CustomerModel customer) throws NoCardException {
        if (customer.getCards() == null) {
            log.error("Card was not provided for customer: {}", customer);
            throw new NoCardException("Please provide at least one card for customer.");
        }
    }
}
