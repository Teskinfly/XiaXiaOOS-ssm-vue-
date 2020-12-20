package test;

import com.teskinfly.domain.Orders;
import com.teskinfly.service.impl.OrderService;
import com.teskinfly.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BigDataTest {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Test
    public void addOrderService() {
        int cnt = 150;
        ExecutorService executorService = Executors.newCachedThreadPool();
        int time = 300000;
        for (int i = 0; i < cnt; i++) {
            executorService.execute(new AddOrder(orderService));
        }
        try {
            TimeUnit.DAYS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }
    @Test
    public void addUserService() {
        int cnt = 150;
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.shutdownNow();
    }
    class AddOrder implements Runnable{
        OrderService orderService;
        AddOrder(OrderService orderService) {
            this.orderService = orderService;
        }
        @Override
        public void run() {
            int time = 0;
            Orders orders = new Orders();
            orders.setOAddress("黄浦区");
            orders.setODate("2020-12-09");
            orders.setOPayment("微信支付");
            orders.setOPrice("18");
            orders.setOStatus("饭店接单中");
            String[] payments = {"微信支付","银行卡","支付宝"};
            String[] dates = {"2020-12-09","2020-11-09","2020-09-09","2020-08-09","2021-01-01"};
            while (time++ < 10000) {
                orders.setOPayment(payments[(int) (Math.random()*3)]);
                orders.setOUId((int) (Math.random()*100));
                orders.setODate(dates[(int) (Math.random()*dates.length)]);
                orderService.addOrderNormal(orders);
//                System.out.println(orders);
                System.out.println(Thread.currentThread()+"--done "+time);
            }
        }
    }
}
