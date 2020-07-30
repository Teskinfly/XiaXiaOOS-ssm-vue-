package com.teskinfly.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter@Getter
public class Cart implements Serializable {
    Food food;
    Integer amount;
    Cart() {

    }

    public Cart(Food food, Integer amount) {
        this.food = food;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "food=" + food +
                ", amount=" + amount +
                '}';
    }
}
