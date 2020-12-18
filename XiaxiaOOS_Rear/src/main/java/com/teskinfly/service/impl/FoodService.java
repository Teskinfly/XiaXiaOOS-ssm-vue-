package com.teskinfly.service.impl;

import com.teskinfly.dao.IFoodDao;
import com.teskinfly.domain.Food;
import com.teskinfly.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService implements IFoodService {
    @Autowired
    IFoodDao foodDao;

    @Override
    public List<Food> findAll() {
        return foodDao.findAll();
    }

    @Override
    public Food findById(Integer fId) {
        return foodDao.findById(fId);
    }

    @Override
    public void addFood(Food food) {
//        food.setFAmount(0);
        foodDao.addFood(food);
    }

    @Override
    public void updateFood(Food food) {
        foodDao.updateFood(food);
    }

    @Override
    public int delFood(Integer fId) {
        return foodDao.delFood(fId);
    }

    @Override
    public List<Food> getFood(int currentPage, int limit) {
        int begin = (currentPage - 1) * limit;
        return foodDao.getLimitFood(begin, limit);
    }
}
