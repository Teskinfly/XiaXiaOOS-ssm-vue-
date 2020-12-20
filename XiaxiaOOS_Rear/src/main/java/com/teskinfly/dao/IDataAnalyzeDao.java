package com.teskinfly.dao;

import com.teskinfly.pojo.charts.DateAndIncome;
import com.teskinfly.pojo.charts.FoodAndAmount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDataAnalyzeDao {
    @Select("select sum(od_f_amount) as amount,f.f_name as fname from orderdetail od, food f where od.od_f_id = f.f_id group by od_f_id ;")
    public List<FoodAndAmount> getFoodAndAmounts();
    @Select("select " +
            "sum(od_f_amount) as amount,f.f_name as fname " +
            "from orderdetail od, food f , orders o " +
            "where od.od_f_id = f.f_id " +
            "and od.od_o_id = o.o_id " +
            "and o.o_date >= #{begin} " +
            "and o.o_date <= #{end} " +
            "group by od_f_id "
    )
    public List<FoodAndAmount> getFoodAndAmountsByDate(@Param("begin") String begin,@Param("end") String end);
    @Select("select o_date as date, sum(o_price) as income from orders where o_date between #{begin} and #{end} group by o_date")
    public List<DateAndIncome> getDateAndIncome(@Param("begin") String begin,@Param("end")String end);
}
