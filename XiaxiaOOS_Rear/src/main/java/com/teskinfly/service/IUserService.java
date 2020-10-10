package com.teskinfly.service;

import com.teskinfly.domain.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User findById(Integer uId);

    boolean correctUser(String uName, String Pwd);

    void addUser(User user);

    void updateUser(User user);

    void updatePwd(String uPwd, Integer uId);

    public User findByName(String uName);

    List<User> getListUser(int total, int pageNum);

    List<User> searchUser(String s);

    void delUser(Integer id);

    String getUserToken(User user);
}
