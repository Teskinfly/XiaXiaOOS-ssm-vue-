package com.teskinfly.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Food implements Serializable {
    Integer fId;
    String fName;
    Integer fPrice;
    String fImg;
    Integer fCId;
    String fDesc;
    Integer fInventory;
//    Integer fAmount;

    public Food() {

    }

    public Food(Integer fId, String fName, Integer fPrice, String fImg, Integer fCId, String fDesc, Integer fInventory) {
        this.fId = fId;
        this.fName = fName;
        this.fPrice = fPrice;
        this.fImg = fImg;
        this.fCId = fCId;
        this.fDesc = fDesc;
        this.fInventory = fInventory;
    }

    @Override
    public String toString() {
        return "Food{" +
                "fId=" + fId +
                ", fName='" + fName + '\'' +
                ", fPrice=" + fPrice +
                ", fImg='" + fImg + '\'' +
                ", fCId=" + fCId +
                ", fDesc='" + fDesc + '\'' +
                ", fInventory=" + fInventory +
                '}';
    }
}
