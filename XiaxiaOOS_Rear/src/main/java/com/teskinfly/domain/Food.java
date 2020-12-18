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
//    Integer fAmount;

    public Food() {

    }

    public Food(String fName, Integer fPrice, String fImg, Integer fCId, String fDesc) {
        this.fName = fName;
        this.fPrice = fPrice;
        this.fImg = fImg;
        this.fCId = fCId;
        this.fDesc = fDesc;
//        this.fAmount = fAmount;
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
                '}';
    }
}
