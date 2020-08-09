package com.teskinfly.dao;

import com.teskinfly.domain.Category;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryDao")
public interface ICategoryDao {
    @Select("select *from category where c_id = #{cId}")
    @Results(id = "categoryMap", value = {
            @Result(column = "c_id", property = "cId", id = true),
            @Result(column = "c_name", property = "cName"),
            @Result(column = "c_des", property = "cDesc"),
            @Result(column = "c_id", property = "foods", many = @Many(select = "com.teskinfly.dao.IFoodDao.findByCId", fetchType = FetchType.EAGER))
    })
    Category findById(Integer cId);

    @Select("select *from category")
    @ResultMap("categoryMap")
    List<Category> findAll();

    @Update("insert category(c_id, c_name, c_des)values(#{cId}, #{cName}, #{cDesc})")
    void addCategory(Category category);

    @Update("update category set c_name = #{cName}, c_des = #{cDesc} where c_id = #{cId}")
    void update(Category category);

    @Update("delete from category where c_id = #{cId}")
    void delCategory(Integer cId);
}
