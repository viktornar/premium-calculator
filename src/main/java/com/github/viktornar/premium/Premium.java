package com.github.viktornar.premium;

import com.github.viktornar.models.ContractModel;
import com.github.viktornar.premium.calculator.NotFoundCalculatorException;
import com.github.viktornar.services.NoCardException;
import com.github.viktornar.services.NoCustomerException;

public interface Premium {
    double getContractPremium(ContractModel contract) throws NotFoundCalculatorException, NoCustomerException, NoCardException;
}
