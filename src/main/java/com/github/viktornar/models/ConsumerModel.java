package com.github.viktornar.models;

import com.github.viktornar.types.ConsumerType;
import lombok.Data;

import java.util.List;

@Data
public class ConsumerModel extends BaseModel {
    ConsumerType consumerType;
    List<CardModel> cards;
}
