package com.teskinfly.service.impl;

import com.teskinfly.dao.IAdminDao;
import com.teskinfly.domain.Admin;
import com.teskinfly.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {
    @Autowired
    IAdminDao adminDao;

    public boolean checkPwd(String aName, String aPwd) {
        Admin byName = adminDao.findByName(aName);
        if (byName == null) return false;
        return byName.getAPwd().equals(aPwd);
    }

    public Admin findByName(String aName) {
        return adminDao.findByName(aName);
    }
}
