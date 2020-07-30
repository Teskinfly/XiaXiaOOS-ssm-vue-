package com.teskinfly.utils;

import com.teskinfly.domain.Category;
import com.teskinfly.domain.Food;
import com.teskinfly.pojo.TableFood;
import com.teskinfly.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class GenerateListTableFood {
    CategoryService categoryService;
    public GenerateListTableFood(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    public GenerateListTableFood(){

    }
    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<TableFood> generate() {
        if (categoryService == null) {
            throw new NullPointerException("缺少categoryService");
        }
        List<TableFood> result = new ArrayList<>();
        List<Category> all = categoryService.findAll();
        for (Category category: all) {
            TableFood tableFood = new TableFood(category);
            for (Food food: category.getFoods()){
                tableFood.addFood(food);
            }
            result.add(tableFood);
        }
        return result;
    }
}
