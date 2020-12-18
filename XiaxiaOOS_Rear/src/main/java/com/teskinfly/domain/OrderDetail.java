package com.teskinfly.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Setter@Getter@NoArgsConstructor
public class OrderDetail implements Serializable {
    Integer odId;
    Integer odOId;
    Integer odFAmount;
    Integer odFId;
    String fName;

    public OrderDetail(Integer odFAmount, Integer odFId, String fName) {
        this.odFAmount = odFAmount;
        this.odFId = odFId;
        this.fName = fName;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "odId=" + odId +
                ", odOId=" + odOId +
                ", odFAmount=" + odFAmount +
                ", odFId=" + odFId +
                ", fName='" + fName + '\'' +
                '}';
    }
}
