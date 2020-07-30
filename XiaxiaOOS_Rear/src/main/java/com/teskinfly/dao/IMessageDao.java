package com.teskinfly.dao;

import com.teskinfly.domain.Message;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("messageDao")
public interface IMessageDao {
    @Select("select *from message")
    @Results(id = "messageMap", value = {
            @Result(id = true, column = "m_id", property = "mId"),
            @Result(column = "m_f_id", property = "mFId"),
            @Result(column = "m_u_id", property = "mUId"),
            @Result(column = "m_content", property = "mComment"),
            @Result(column = "m_date", property = "mDate"),
            @Result(column = "m_f_id", property = "food", one = @One(select = "com.teskinfly.dao.IFoodDao.findById", fetchType = FetchType.EAGER)),//error
            @Result(column = "m_u_id", property = "user", one = @One(select = "com.teskinfly.dao.IUserDao.findById", fetchType = FetchType.EAGER))
    })
    List<Message> findAll();

    @Select("select *from message where m_f_id = #{mFId}")
    @ResultMap("messageMap")
    List<Message> findByFoodId(Integer mFId);

    @Select("select *from message where m_u_id = #{mUId}")
    @ResultMap("messageMap")
    List<Message> findByUserId(Integer mUId);

    @Select("select *from message where m_u_id = #{mUId} and m_f_id = #{mFId}")
    @ResultMap("messageMap")
    Message findByUserAndFood(@Param("mFId") Integer mFId, @Param("mUId") Integer mUId);

    @Update("insert into message (m_f_id, m_content, m_u_id, m_date)values(#{mFId},#{mComment},#{mUId},#{mDate})")
    void addMessage(Message message);

    @Update("delete from message where m_id = #{mId}")
    void delMessage(Integer mId);

    @Select("select *from message where m_id = #{mId}")
    @ResultMap("messageMap")
    Message findById(Integer mId);
}
