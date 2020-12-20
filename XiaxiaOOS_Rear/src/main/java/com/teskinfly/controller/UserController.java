package com.teskinfly.controller;

import com.teskinfly.domain.User;
import com.teskinfly.pojo.send.DataReturn;
import com.teskinfly.pojo.send.ReturnCode;
import com.teskinfly.pojo.send.TableReturn;
import com.teskinfly.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
@ResponseBody
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    DataReturn login(@RequestBody User user) {
//        System.out.println(user);
        if (!userService.correctUser(user.getUName(), user.getUPwd()))
            return new DataReturn(ReturnCode.FAIL);
        User u = userService.findByName(user.getUName());
        u.setUPwd("");
        String s = userService.getUserToken(u);
        return new DataReturn(new ArrayList() {{
            add(u);
        }}, ReturnCode.SUCCESS, s);
    }

    @RequestMapping("/getUserList")
    TableReturn getUserList(@RequestBody TableReturn tableReturn, HttpServletRequest request) {
//        System.out.println(tableReturn);
        String authorization = request.getHeader("Authorization");
        if (authorization == null)
            return new TableReturn(tableReturn.getTotal(), tableReturn.getPageNum(), null, ReturnCode.WITHOUTTOKEN);
        if (tableReturn.getQuery() == "") {
            List<User> listUser = userService.getListUser(tableReturn.getTotal(), tableReturn.getPageNum());
            tableReturn.setData(listUser);
            return tableReturn;
        }
        List<User> users = userService.searchUser(tableReturn.getQuery());
        tableReturn.setData(users);
        return tableReturn;
    }

    @RequestMapping("/addUser")
    DataReturn addUser(@RequestBody User user, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) return new DataReturn(ReturnCode.WITHOUTTOKEN);
//        System.out.println(user);
        if (user == null) return new DataReturn(ReturnCode.FAIL);
        userService.addUser(user);
        return new DataReturn(ReturnCode.SUCCESS);
    }

    @RequestMapping(value = "/delUser", method = RequestMethod.GET)
    DataReturn delUser(@RequestParam("uid") Integer uId, HttpServletRequest request) {
        System.out.println(uId);
        String authorization = request.getHeader("Authorization");
        if (authorization == null) return new DataReturn(ReturnCode.WITHOUTTOKEN);
//        return new DataReturn((ReturnCode.SUCCESS));
        userService.delUser(uId);
        return new DataReturn(ReturnCode.SUCCESS);
    }
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    DataReturn update(@RequestBody User user) {
        userService.updateUser(user);
        return new DataReturn(ReturnCode.SUCCESS);
    }
}
