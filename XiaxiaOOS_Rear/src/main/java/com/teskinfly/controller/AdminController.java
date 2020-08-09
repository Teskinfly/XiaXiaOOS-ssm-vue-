package com.teskinfly.controller;

import com.teskinfly.domain.Admin;
import com.teskinfly.domain.User;
import com.teskinfly.pojo.DataReturn;
import com.teskinfly.pojo.ReturnCode;
import com.teskinfly.pojo.TableReturn;
import com.teskinfly.service.impl.AdminService;
import com.teskinfly.service.impl.UserService;
import com.teskinfly.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    JWTUtils jwt;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    DataReturn login(@RequestBody Admin admin) {
        if (!adminService.checkPwd(admin.getAName(), admin.getAPwd()))
            return new DataReturn(ReturnCode.FAIL);
        Admin byName = adminService.findByName(admin.getAName());
        String s = jwt.create(byName.getAId(), byName.getAName());
        return new DataReturn(ReturnCode.SUCCESS, s);
    }
}
