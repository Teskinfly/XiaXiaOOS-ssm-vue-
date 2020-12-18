package com.teskinfly.dao;

import com.teskinfly.pojo.charts.FoodAndAmount;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDataAnalyzeDao {
    @Select("select sum(od_f_amount) as amount,f.f_name as fname from orderdetail od, food f where od.od_f_id = f.f_id group by od_f_id ;")
    public List<FoodAndAmount> getFoodAndAmounts();

}
