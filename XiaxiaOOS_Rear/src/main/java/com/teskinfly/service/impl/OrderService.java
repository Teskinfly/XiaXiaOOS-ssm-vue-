package com.teskinfly.service.impl;

import com.teskinfly.dao.IOrderDao;
import com.teskinfly.domain.Orders;
import com.teskinfly.domain.User;
import com.teskinfly.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderDao orderDao;

    @Override
    public List<Orders> findAll(int total, int pageNum) {
        return orderDao.findAll(total*(pageNum-1),total);
    }

    @Override
    public void addOrder(Orders order, User user) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        order.setODate(simpleDateFormat.format(date));
        order.setOStatus("饭店接单中");
        order.setOAddress(user.getUAddress());
        orderDao.addOrder(order);
    }

    @Override
    public Orders findById(Integer oId) {
        return orderDao.findById(oId);
    }

    @Override
    public void changeStatus(String status) {
    }

    @Override
    public List<Orders> findByUId(Integer uId) {
        return orderDao.findByUId(uId);
    }

    @Override
    public void delOrders(Integer oId) {
        orderDao.delOrders(oId);
    }

    @Override
    public List<Orders> findByDate(Date beginDate, Date endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(beginDate)+" "+simpleDateFormat.format(endDate));
        List<Orders> specificOrders = orderDao.getSpecificOrders(simpleDateFormat.format(beginDate), simpleDateFormat.format(endDate));
        return specificOrders;
    }

    public void changeStatus(String status, Integer oId) {
        if (status.equals("饭店接单中")) {
            status = "外卖小哥送餐中";
        } else {
            status = "用户已接收";
        }
        orderDao.updateStatus(status,oId);
    }
}
