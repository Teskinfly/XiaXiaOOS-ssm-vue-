package com.teskinfly.pojo;

import lombok.Getter;
import lombok.Setter;

//购物车物品项
@Setter
@Getter
public class CartItem {
    String id;
    int amount;

    public CartItem() {
    }

    public CartItem(String id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                '}';
    }
}
