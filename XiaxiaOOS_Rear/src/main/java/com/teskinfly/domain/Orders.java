package com.teskinfly.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Orders implements Serializable {
    Integer oId;
    String oDate;
    String oPayment;
    Integer oUId;
    String oPrice;
    String oStatus;
    String oContent;
    String oAddress;
    User user;

    public Orders() {

    }

    public Orders(Integer oId, String oDate, String oPayment, Integer oUId, String oPrice, String oStatus, String oContent, String oAddress, User user) {
        this.oId = oId;
        this.oDate = oDate;
        this.oPayment = oPayment;
        this.oUId = oUId;
        this.oPrice = oPrice;
        this.oStatus = oStatus;
        this.oContent = oContent;
        this.oAddress = oAddress;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oId=" + oId +
                ", oDate='" + oDate + '\'' +
                ", oPayment='" + oPayment + '\'' +
                ", oUId=" + oUId +
                ", oPrice='" + oPrice + '\'' +
                ", oStatus='" + oStatus + '\'' +
                ", oContent='" + oContent + '\'' +
                ", user=" + user +
                '}';
    }
}
