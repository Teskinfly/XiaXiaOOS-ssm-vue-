package com.teskinfly.controller;

import com.teskinfly.domain.Admin;
import com.teskinfly.pojo.send.DataReturn;
import com.teskinfly.pojo.send.ReturnCode;
import com.teskinfly.service.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    AdminService adminService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    DataReturn login(@RequestBody Admin admin) {
        if (!adminService.checkPwd(admin.getAName(), admin.getAPwd()))
            return new DataReturn(ReturnCode.FAIL);
        Admin byName = adminService.findByName(admin.getAName());
        String token = adminService.getToken(byName);
        return new DataReturn(ReturnCode.SUCCESS, token);
    }
}
