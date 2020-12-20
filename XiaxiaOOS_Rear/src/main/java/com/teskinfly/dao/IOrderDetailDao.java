package com.teskinfly.dao;

import com.teskinfly.domain.OrderDetail;
import com.teskinfly.pojo.charts.FoodAndAmount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailDao {
    @Select("select od.*,f.f_name as f_name from orderdetail od, food f where od_o_id = #{orderId} and f.f_id = od.od_f_id")
    @Results(id = "orderDetailMap",
            value = {@Result(id = true, column = "od_id", property = "odId"),
                    @Result(column = "od_o_id", property = "odOId"),
                    @Result(column = "od_f_id", property = "odFId"),
                    @Result(column = "od_f_amount", property = "odFAmount"),
                    @Result(column = "f_name", property = "fName")
    }
    )
    public List<OrderDetail> findByOrderId(Integer orderId);
    @Insert("insert into orderdetail (od_o_id, od_f_id, od_f_amount) values(#{odOId}, #{odFId},#{odFAmount})")
    public boolean addOD(OrderDetail orderDetail);
    @Delete("delete from orderdetail where od_o_id = #{oId}")
    public boolean delByOId(Integer oId);
}
