package com.teskinfly.service.impl;

import com.teskinfly.dao.IOrderDao;
import com.teskinfly.dao.IOrderDetailDao;
import com.teskinfly.domain.OrderDetail;
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
    @Autowired
    IOrderDetailDao orderDetailDao;
    @Override
    public List<Orders> findAll(int total, int pageNum) {
        List<Orders> all = orderDao.findAll(total * (pageNum - 1), total);
        setOContent(all);
        return all;
    }

    @Override
    public void addOrder(Orders order, User user) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        order.setODate(simpleDateFormat.format(date));
        order.setOStatus("饭店接单中");
        order.setOAddress(user.getUAddress());
        Integer maxId = orderDao.getMaxId();
        if (maxId == null) {
            maxId = 0;
        }
        order.setOId(maxId+1);
        for (OrderDetail orderDetail: order.getOrderDetailList()) {
            orderDetail.setOdOId(order.getOId());
            orderDetailDao.addOD(orderDetail);
        }
        orderDao.addOrder(order);
    }

    @Override
    public Orders findById(Integer oId) {
        Orders byId = orderDao.findById(oId);
        setOContent(new ArrayList<Orders>(){{add(byId);}});
        return byId;
    }

    @Override
    public void changeStatus(String status) {
    }

    @Override
    public List<Orders> findByUId(Integer uId) {
        List<Orders> byUId = orderDao.findByUId(uId);
        setOContent(byUId);
        return byUId;
    }

    @Override
    public void delOrders(Integer oId) {
        orderDao.delOrders(oId);
        orderDetailDao.delByOId(oId);
    }

    @Override
    public List<Orders> findByDate(Date beginDate, Date endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(simpleDateFormat.format(beginDate) + " " + simpleDateFormat.format(endDate));
        List<Orders> specificOrders = orderDao.getSpecificOrders(simpleDateFormat.format(beginDate), simpleDateFormat.format(endDate));
        return specificOrders;
    }

    public void changeStatus(String status, Integer oId) {
        if (status.equals("饭店接单中")) {
            status = "外卖小哥送餐中";
        } else {
            status = "用户已接收";
        }
        orderDao.updateStatus(status, oId);
    }
    //将OrderDetail内容封装进oContent
    public void setOContent(List<Orders> orderList) {
        for (Orders orders: orderList) {
            List<OrderDetail> orderDetailList = orders.getOrderDetailList();
            StringBuffer sb = new StringBuffer();
            for (OrderDetail orderDetail: orderDetailList) {
                sb.append(orderDetail.getFName());
                sb.append("*");
                sb.append(orderDetail.getOdFAmount());
                sb.append(" ");
            }
            orders.setOContent(sb.toString());
            orders.setOrderDetailList(null);
        }
    }
}
