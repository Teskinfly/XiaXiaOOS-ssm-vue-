package com.teskinfly.dao;

import com.teskinfly.domain.Admin;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("adminDao")
public interface IAdminDao {
    @Select("select *from admin where a_name = #{aName}")
    @Results(id = "adminMap", value = {
            @Result(id = true, column = "a_id", property = "aId"),
            @Result(column = "a_pwd", property = "aPwd"),
            @Result(column = "a_name", property = "aName")
    })
    Admin findByName(String aName);
}
