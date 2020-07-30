package com.teskinfly.service;

import com.teskinfly.domain.Food;

import java.util.List;

public interface IFoodService {
    List<Food> findAll();

    Food findById(Integer fId);

    void addFood(Food food);

    void updateFood(Food food);

    int delFood(Integer fId);

    List<Food> getFood(int page, int limit);
}
