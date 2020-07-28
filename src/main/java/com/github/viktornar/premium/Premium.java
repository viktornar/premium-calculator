package com.github.viktornar.premium;

import com.github.viktornar.models.ContractModel;
import com.github.viktornar.premium.calculator.NotFoundCalculatorException;

public interface Premium {
    double getContractPremium(ContractModel contract) throws NotFoundCalculatorException;
}
