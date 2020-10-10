package com.teskinfly.service.impl;

import com.teskinfly.dao.IAdminDao;
import com.teskinfly.domain.Admin;
import com.teskinfly.service.IAdminService;
import com.teskinfly.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AdminService implements IAdminService {
    @Autowired
    IAdminDao adminDao;
    @Autowired
    JWTUtils jwtUtils;
    @Autowired
    RedisTemplate redisTemplate;

    public boolean checkPwd(String aName, String aPwd) {
        Admin byName = adminDao.findByName(aName);
        if (byName == null) return false;
        return byName.getAPwd().equals(aPwd);
    }

    @Override
    public String getToken(Admin admin) {
        String s = jwtUtils.create(admin.getAId(), admin.getAName());
        redisTemplate.opsForValue().set(s,true);
        redisTemplate.expire(s,10,TimeUnit.MINUTES);
        return s;
    }

    public Admin findByName(String aName) {
        return adminDao.findByName(aName);
    }
    
}
