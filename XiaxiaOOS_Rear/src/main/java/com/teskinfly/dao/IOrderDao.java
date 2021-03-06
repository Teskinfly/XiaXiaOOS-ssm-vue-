package com.teskinfly.dao;

import com.teskinfly.domain.Orders;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDao")
public interface IOrderDao {
//    order by o_date
    @Select("select o_id, o_u_id, o_date, o_payment, o_address, o_price, o_status from orders order by o_id desc limit #{begin},#{end}")
    @Results(id = "orderMap", value = {
            @Result(id = true, column = "o_id", property = "oId"),
            @Result(column = "o_date", property = "oDate"),
            @Result(column = "o_payment", property = "oPayment"),
            @Result(column = "o_u_id", property = "oUId"),
            @Result(column = "o_price", property = "oPrice"),
            @Result(column = "o_status", property = "oStatus"),
            @Result(column = "o_address", property = "oAddress"),
            @Result(column = "o_u_id", property = "user", one = @One(select = "com.teskinfly.dao.IUserDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "o_id", property = "orderDetailList",many = @Many(select = "com.teskinfly.dao.IOrderDetailDao.findByOrderId", fetchType = FetchType.EAGER))
    })
    List<Orders> findOrder(@Param("begin") int begin, @Param("end") int end);

    @Select("select o_id, o_u_id, o_date, o_payment, o_address, o_price, o_status from orders where o_id = #{oId}")
    @ResultMap("orderMap")
    Orders findById(Integer oId);

    @Update("insert into orders (o_id, o_date, o_payment, o_u_id, o_price, o_status, o_address) values(#{oId}, #{oDate},#{oPayment},#{oUId},#{oPrice},#{oStatus},#{oAddress})")
    void addOrder(Orders orders);

    @Update("update orders set o_status = #{oStatus} where o_id = #{oId}")
    void updateStatus(@Param("oStatus") String oStatus, @Param("oId") Integer oId);

    @Select("select o_id, o_u_id, o_date, o_payment, o_address, o_price, o_status from orders where o_u_id = #{oUId} order by o_id desc limit 0,10")
    @ResultMap("orderMap")
    List<Orders> findByUId(Integer oUId);

    @Update("delete from orders where o_id = #{oId}")
    void delOrders(Integer oId);

//    @Select("select o_id, o_u_id, o_date, o_payment, o_address, o_price, o_status from orders where o_date >= #{begin} and o_date <= #{end} order by o_date desc")
//    @ResultMap("orderMap")
//    List<Orders> getSpecificOrders(@Param("begin") String begin, @Param("end") String end);
    @Select("select max(o_id) from orders")
    Integer getMaxId();
    @Update("insert into orders (o_date, o_payment, o_u_id, o_price, o_status, o_address) values(#{oDate},#{oPayment},#{oUId},#{oPrice},#{oStatus},#{oAddress})")
    void addNormalOrder(Orders orders);
}
