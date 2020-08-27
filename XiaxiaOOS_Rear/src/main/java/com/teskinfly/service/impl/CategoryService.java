package com.teskinfly.service.impl;

import com.teskinfly.dao.ICategoryDao;
import com.teskinfly.domain.Category;
import com.teskinfly.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    public final String CKEY = "allCategory";
    @Autowired
    ICategoryDao categoryDao;
    @Autowired
            @Qualifier("categoryRedisTemplate")
    RedisTemplate categoryRedisTemplate;
    @Override
    public List<Category> findAll() {
        List<Category> all = categoryDao.findAll();
        return all;
    }

    @Override
    public Category findById(Integer cId) {
        Category category = (Category)categoryRedisTemplate.opsForHash().get(CKEY, cId);
        if (category == null) return categoryDao.findById(cId);
        return category;
    }

    @Override
    public void addCategory(Category category) {
        categoryDao.addCategory(category);
        Category byName = categoryDao.findByName(category.getCName());
        if (byName == null) return;
        categoryRedisTemplate.opsForHash().put(CKEY,String.valueOf(byName.getCId()),byName);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRedisTemplate.opsForHash().put(CKEY,category.getCId()+"",category);
        categoryDao.update(category);
    }

    @Override
    public void delCategory(Integer cId) {
        categoryRedisTemplate.opsForHash().delete(CKEY,cId+"");
        categoryDao.delCategory(cId);
    }
}
