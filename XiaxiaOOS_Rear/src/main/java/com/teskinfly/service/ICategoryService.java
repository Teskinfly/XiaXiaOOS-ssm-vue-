package com.teskinfly.service;

import com.teskinfly.domain.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();

    Category findById(Integer cId);

    void addCategory(Category category);

    void updateCategory(Category category);

    void delCategory(Integer cId);

}
