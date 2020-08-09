package com.teskinfly.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class Category implements Serializable {
    Integer cId;
    String cName;
    String cDesc;
    List<Food> foods;

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cDesc='" + cDesc + '\'' +
                ", foods=" + foods +
                '}';
    }

    public Category(Integer cId, String cName, String cDesc, List<Food> foods) {
        this.cId = cId;
        this.cName = cName;
        this.cDesc = cDesc;
        this.foods = foods;
    }
}
