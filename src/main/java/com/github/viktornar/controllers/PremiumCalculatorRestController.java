package com.github.viktornar.controllers;

import com.github.viktornar.models.ContractModel;
import com.github.viktornar.premium.calculator.NotFoundCalculatorException;
import com.github.viktornar.services.ContractPremiumService;
import com.github.viktornar.services.NoCardException;
import com.github.viktornar.services.NoCustomerException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class PremiumCalculatorRestController {
    ContractPremiumService contractPremiumService;

    @Autowired
    public PremiumCalculatorRestController(ContractPremiumService contractPremiumService) {
        this.contractPremiumService = contractPremiumService;
    }

    @PostMapping("/premiumForGivenContract")
    ResultResponse calculateContractPremium(@RequestBody ContractModel contract) throws NotFoundCalculatorException, NoCustomerException, NoCardException {
        log.info("Got request with contract: {}", contract);
        double premium = contractPremiumService.getContractPremium(contract);
        log.info("Calculated premium for given contract: {}", premium);
        return new ResultResponse(contract.getId(), premium);
    }

    @Data
    static class ResultResponse {
        private final String contractId;
        private final double premium;
    }

}
