package com.teskinfly.service;

import com.teskinfly.domain.Admin;

public interface IAdminService {
    public boolean checkPwd(String aName, String aPwd);
    public String getToken(Admin admin);
}
