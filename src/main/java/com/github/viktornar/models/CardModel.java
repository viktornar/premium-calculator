package com.github.viktornar.models;

import com.github.viktornar.types.RiskType;
import lombok.Data;

@Data
public class CardModel extends BaseModel {
    Double insuredAmount;
    RiskType riskType;
}
