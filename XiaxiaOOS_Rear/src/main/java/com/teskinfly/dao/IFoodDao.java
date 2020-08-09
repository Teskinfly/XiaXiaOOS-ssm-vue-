package com.teskinfly.dao;

import com.teskinfly.domain.Food;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("foodDao")
public interface IFoodDao {
    @Select("select *from food")
    @Results(id = "foodMap", value = {
            @Result(id = true, column = "f_id", property = "fId"),
            @Result(column = "f_name", property = "fName"),
            @Result(column = "f_price", property = "fPrice"),
            @Result(column = "f_img", property = "fImg"),
            @Result(column = "f_c_id", property = "fCId"),
            @Result(column = "f_des", property = "fDesc"),
            @Result(column = "f_amount", property = "fAmount"),
//            @Result(column = "f_c_id", property = "category", one = @One(select = "com.teskinfly.dao.ICategoryDao.findById", fetchType = FetchType.EAGER))
    })
    List<Food> findAll();

    @Select("insert into food(f_name, f_price, f_img, f_c_id, f_des, f_amount)values(#{fName},#{fPrice},#{fImg},#{fCId},#{fDesc},#{fAmount})")
    void addFood(Food food);

    @Update("update food set f_name = #{fName}, f_img = #{fImg}, f_c_id = #{fCId}, f_price = #{fPrice}, f_amount = #{fAmount}, f_des = #{fDesc} where f_id = #{fId}")
    void updateFood(Food food);

    @Select("select *from food where f_id = #{fId}")
    @ResultMap("foodMap")
    Food findById(Integer fId);

    @Update("delete from food where f_id = #{fId}")
    int delFood(Integer fId);

    @Select("select *from food limit #{lo} , #{hi}")
    @ResultMap("foodMap")
    List<Food> getLimitFood(@Param("lo") int lo, @Param("hi") int hi);

    @Select("select count(*) from food")
    int getTotalCount();

    @Select("select *from food where f_name = #{fName}")
    @ResultMap("foodMap")
    Food findByName(String fName);

    @Select("select *from food where f_c_id = #{cId}")
    @ResultMap("foodMap")
    List<Food> findByCId(Integer cId);
}
