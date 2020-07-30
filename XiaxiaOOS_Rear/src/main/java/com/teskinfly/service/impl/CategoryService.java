package com.teskinfly.service.impl;

import com.teskinfly.dao.ICategoryDao;
import com.teskinfly.domain.Category;
import com.teskinfly.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Integer cId) {
        return categoryDao.findById(cId);
    }

    @Override
    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void delCategory(Integer cId) {
        categoryDao.delCategory(cId);
    }
}
