package com.github.viktornar.models;

import com.github.viktornar.types.StatusType;
import lombok.Data;

import java.util.List;

@Data
public class ContractModel {
    private String id;
    private StatusType statusType;
    private List<CustomerModel> consumers;
}
