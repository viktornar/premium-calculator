package com.github.viktornar.controllers;

import com.github.viktornar.models.ContractModel;
import com.github.viktornar.premium.calculator.NotFoundCalculatorException;
import com.github.viktornar.services.ContractPremiumService;
import com.github.viktornar.services.NoCardException;
import com.github.viktornar.services.NoCustomerException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PremiumCalculatorRestController {
    ContractPremiumService contractPremiumService;

    @Autowired
    public PremiumCalculatorRestController(ContractPremiumService contractPremiumService) {
        this.contractPremiumService = contractPremiumService;
    }

    @PostMapping("/premiumForGivenContract")
    ResultResponse calculateContractPremium(ContractModel contract) throws NotFoundCalculatorException, NoCustomerException, NoCardException {
        double premium = contractPremiumService.getContractPremium(contract);
        return new ResultResponse(contract.getId(), premium);
    }

    @Data
    static class ResultResponse {
        private final String contractId;
        private final double premium;
    }

}
