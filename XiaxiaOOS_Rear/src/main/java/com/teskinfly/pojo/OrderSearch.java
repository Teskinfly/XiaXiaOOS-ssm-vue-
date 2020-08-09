package com.teskinfly.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderSearch {
    String status;
    String bTime;
    String eTime;
    String order;
    String id;

    public OrderSearch() {
    }

    public OrderSearch(String status, String bTime, String eTime, String order, String id) {
        this.status = status;
        this.bTime = bTime;
        this.eTime = eTime;
        this.order = order;
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrderSearch{" +
                "status='" + status + '\'' +
                ", bTime='" + bTime + '\'' +
                ", eTime='" + eTime + '\'' +
                ", order='" + order + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
