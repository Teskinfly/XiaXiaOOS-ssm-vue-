package com.teskinfly.service.impl;

import com.teskinfly.dao.IUserDao;
import com.teskinfly.domain.User;
import com.teskinfly.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Integer uId) {
        return userDao.findById(uId);
    }

    @Override
    public boolean correctUser(String uName, String Pwd) {
        User byName = userDao.findByName(uName);
        if (byName == null)
            return false;
        return byName.getUPwd().equals(Pwd) ? true : false;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateInfo(user);
    }

    @Override
    public void updatePwd(String uPwd, Integer uId) {
        userDao.updatePwd(uPwd, uId);
    }

    public User findByName(String uName) {
        return userDao.findByName(uName);
    }

    public List<User> getListUser(int total, int pageNum) {
        return userDao.getListUser(total*(pageNum-1),total);
    }

    @Override
    public List<User> searchUser(String s) {
         return userDao.searchByName(s);
    }
    @Override
    public void delUser(Integer id){
        userDao.delUser(id);
    }


}
