package com.github.viktornar.models;

import com.github.viktornar.types.StatusType;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractModel {
    private String id;
    private StatusType statusType;
    private List<CustomerModel> customers;
}
