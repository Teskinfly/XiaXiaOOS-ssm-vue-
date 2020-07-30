package com.teskinfly.controller;

import com.teskinfly.domain.Orders;
import com.teskinfly.pojo.*;
import com.teskinfly.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/order")
@ResponseBody
@CrossOrigin
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @RequestMapping("/getSpecificOrders")
    public TableReturn getSpecificOrders(OrderSearch orderSearch) {
        return new TableReturn(null,ReturnCode.FAIL);
    }
    @RequestMapping("/getFormat")
    public TableReturn getFormat() {
        return new TableReturn(new ArrayList(){{add(new TableReturn());}},ReturnCode.SUCCESS);
    }
    @RequestMapping("/getOrders")
    public TableReturn getOrders(@RequestBody TableReturn tableReturn) {
        List<Orders> all = orderService.findAll(tableReturn.getTotal(), tableReturn.getPageNum());
        return new TableReturn(all,ReturnCode.SUCCESS);
    }
    @RequestMapping("/changeStatus")
    public DataReturn changeStatus(Integer oid) {
        Orders byId = orderService.findById(oid);
        if (byId == null|| byId.getOStatus().equals("用户已接收")) {
            return new DataReturn(ReturnCode.FAIL);
        }
        orderService.changeStatus(byId.getOStatus(),byId.getOId());
        return new DataReturn(ReturnCode.SUCCESS);
    }
}
