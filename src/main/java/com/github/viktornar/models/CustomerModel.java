package com.github.viktornar.models;

import com.github.viktornar.types.CostumerType;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CustomerModel extends BaseModel {
    private CostumerType costumerType;
    private List<CardModel> cards;

    public CustomerModel(String name, CostumerType costumerType, List<CardModel> cards) {
        super(name);
        this.costumerType = costumerType;
        this.cards = cards;
    }
}
