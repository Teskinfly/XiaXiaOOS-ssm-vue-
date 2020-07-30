package com.teskinfly.dao;

import com.teskinfly.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface IUserDao {
    @Select("select *from user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "u_id", property = "uId"),
            @Result(column = "u_name", property = "uName"),
            @Result(column = "u_pwd", property = "uPwd"),
            @Result(column = "u_address", property = "uAddress"),
            @Result(column = "u_pn", property = "uPhone")
    })
    List<User> findAll();
    @Select("select *from user where u_id = #{uId}")
    @ResultMap("userMap")
    User findById(Integer uId);
    @Select("select *from user where u_Name = #{uName}")
    @ResultMap("userMap")
    User findByName(String uName);
    @Update("insert into user(u_name, u_pwd, u_pn, u_address)values(#{uName},#{uPwd},#{uPhone},#{uAddress})")
    void addUser(User user);
    @Update("update user set u_pwd = #{uPwd} where u_id = #{uId}")
//error
    void updatePwd(@Param("uPwd") String uPwd, @Param("uId") Integer uId);
    @Update("update user set u_id = #{uId}, u_pwd = #{uPwd}, u_address = #{uAddress}, u_pn = #{uPhone}, u_name = #{uName} where u_id = #{uId}")
    void updateInfo(User user);
    @Select("select *from user limit #{begin}, #{end}")
    @ResultMap("userMap")
    List<User> getListUser(@Param("begin") int begin, @Param("end") int end);
    @Select("select *from user where u_name like '%${value}%' ")
    @ResultMap("userMap")
    List<User> searchByName(String s);
    @Delete("delete from user where u_id = #{id}")
    void delUser(Integer id);
}
