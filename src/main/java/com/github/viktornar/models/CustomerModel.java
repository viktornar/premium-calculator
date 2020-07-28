package com.github.viktornar.models;

import com.github.viktornar.types.CustomerType;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CustomerModel extends BaseModel {
    private CustomerType customerType;
    private List<CardModel> cards;

    public CustomerModel(String name, CustomerType customerType, List<CardModel> cards) {
        super(name);
        this.customerType = customerType;
        this.cards = cards;
    }
}
