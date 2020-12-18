package com.teskinfly.pojo.charts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor
public class FoodAndAmount {
    String fName;
    int amount;

    public FoodAndAmount(String fName, int amount) {
        this.fName = fName;
        this.amount = amount;
    }
}
