package com.github.viktornar.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.viktornar.types.CardType;
import com.github.viktornar.types.CostumerType;
import com.github.viktornar.types.RiskType;
import com.github.viktornar.types.StatusType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class ContractModelTest {
    private final static String CONTRACT_JSON = "{\"id\":\"LT2078-5252-55\",\"statusType\":\"APPROVED\",\"customers\":[{\"name\":\"Jonas Jonaitis\",\"costumerType\":\"FATHER\",\"cards\":[{\"cardType\":\"DEBIT\",\"insuredSum\":1000.0,\"riskType\":\"FRAUD\"}]}]}";
    private ContractModel contract;
    private List<CustomerModel> customers;

    @Before
    public void setUp() {
        contract = new ContractModel();
        contract.setId("LT2078-5252-55");
        contract.setStatusType(StatusType.APPROVED);
        customers = Collections.singletonList(
                new CustomerModel(
                        "Jonas Jonaitis",
                        CostumerType.FATHER,
                        Collections.singletonList(
                                new CardModel(
                                        CardType.DEBIT,
                                        1000.00,
                                        RiskType.FRAUD
                                )
                        )
                )
        );

        contract.setCustomers(customers);
    }

    @Test
    public void shouldSerializeContract() throws JsonProcessingException {
        String serializedContract = new ObjectMapper().writeValueAsString(contract);
        Assert.assertEquals(CONTRACT_JSON, serializedContract);
    }

    @Test
    public void shouldDeserializeContract() throws JsonProcessingException {
        ContractModel contract = new ObjectMapper().readValue(CONTRACT_JSON, ContractModel.class);
        Assert.assertEquals("LT2078-5252-55", contract.getId());
        Assert.assertEquals(StatusType.APPROVED, contract.getStatusType());
        Assert.assertEquals(customers, contract.getCustomers());
    }
}
