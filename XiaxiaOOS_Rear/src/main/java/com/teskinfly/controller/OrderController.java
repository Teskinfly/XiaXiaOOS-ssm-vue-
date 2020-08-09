package com.teskinfly.controller;

import com.teskinfly.domain.Orders;
import com.teskinfly.domain.User;
import com.teskinfly.pojo.*;
import com.teskinfly.service.impl.OrderService;
import com.teskinfly.service.impl.UserService;
import com.teskinfly.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/order")
@ResponseBody
@CrossOrigin
@Controller
public class OrderController {
    @Autowired
    JWTUtils jwt;
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @RequestMapping("/getSpecificOrders")
    public TableReturn getSpecificOrders(OrderSearch orderSearch) {
        return new TableReturn(null, ReturnCode.FAIL);
    }

    @RequestMapping("/getFormat")
    public TableReturn getFormat() {
        return new TableReturn(new ArrayList() {{
            add(new TableReturn());
        }}, ReturnCode.SUCCESS);
    }

    @RequestMapping("/getOrders")
    public TableReturn getOrders(@RequestBody TableReturn tableReturn) {
        List<Orders> all = orderService.findAll(tableReturn.getTotal(), tableReturn.getPageNum());
        return new TableReturn(all, ReturnCode.SUCCESS);
    }

    @RequestMapping("/changeStatus")
    public DataReturn changeStatus(Integer oid) {
        System.out.println(oid);
        Orders byId = orderService.findById(oid);
        if (byId == null || byId.getOStatus().equals("用户已接收")) {
            return new DataReturn(ReturnCode.FAIL);
        }
        orderService.changeStatus(byId.getOStatus(), byId.getOId());
        return new DataReturn(ReturnCode.SUCCESS);
    }

    @RequestMapping("/addOrder")
    public DataReturn addOrder(@RequestBody Orders order) {
        if (order == null) return new DataReturn(ReturnCode.FAIL);
        User byId = userService.findById(order.getOUId());
        orderService.addOrder(order, byId);
        return new DataReturn(ReturnCode.SUCCESS);
    }

    @RequestMapping("/getOrdersByUser")
    public DataReturn getOrdersByUser(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
//        System.out.println(authorization);
//        System.out.println(jwt.parse(authorization));
//        System.out.println(jwt);
        Claims parse = jwt.parse(authorization);
//        System.out.println(parse);
//        System.out.println(parse.getId());
        List<Orders> byUId = orderService.findByUId(Integer.valueOf(parse.getId()));
        return new DataReturn(ReturnCode.SUCCESS, byUId);
    }

    @RequestMapping("/delOrder")
    public DataReturn delOrder(Integer oid) {
        if (oid == null) return new DataReturn(ReturnCode.FAIL);
        orderService.delOrders(oid);
        return new DataReturn(ReturnCode.SUCCESS);
    }
}
