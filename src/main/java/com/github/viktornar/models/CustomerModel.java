package com.github.viktornar.models;

import com.github.viktornar.types.ConsumerType;
import lombok.Data;

import java.util.List;

@Data
public class CustomerModel extends BaseModel {
    private ConsumerType consumerType;
    private List<CardModel> cards;
}
