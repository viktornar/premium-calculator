package com.github.viktornar.services;

import com.github.viktornar.models.ContractModel;
import com.github.viktornar.premium.Premium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractPremiumService implements Premium {
    private AggregatorService aggregatorService;

    @Autowired
    public ContractPremiumService(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @Override
    public double getContractPremium(ContractModel contract) {
        return 0;
    }
}
