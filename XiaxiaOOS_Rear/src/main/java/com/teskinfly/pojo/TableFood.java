package com.teskinfly.pojo;

import com.teskinfly.domain.Category;
import com.teskinfly.domain.Food;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter@Getter
public class TableFood {
    String id;
    String rid;
    String name;
    String desc;
    Integer price;
    Integer amount;
    String type;
    List<TableFood> next = new ArrayList<>();
    public TableFood() {

    }
    public TableFood(Category category) {
        this.name = category.getCName();
        this.desc = category.getCDesc();
        this.id = category.getCId()+"";
        this.rid = category.getCId()+" ";
        this.type = "category";
    }
    public boolean addFood(Food food) {
        TableFood tableFood = new TableFood();
        tableFood.setAmount(food.getFAmount());
        tableFood.setDesc(food.getFDesc());
        tableFood.setName(food.getFName());
        tableFood.setPrice(food.getFPrice());
        tableFood.setId(food.getFCId()+"-"+food.getFId());
        tableFood.setRid(food.getFId()+" ");
        return next.add(tableFood);
    }
    @Override
    public String toString() {
        return "TableFood{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price='" + price + '\'' +
                ", amount='" + amount + '\'' +
                ", next=" + next +
                '}';
    }
}
