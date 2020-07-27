package com.github.viktornar.models;

import com.github.viktornar.types.CardType;
import com.github.viktornar.types.RiskType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardModel {
    private CardType cardType;
    private Double insuredAmount;
    private RiskType riskType;
}
