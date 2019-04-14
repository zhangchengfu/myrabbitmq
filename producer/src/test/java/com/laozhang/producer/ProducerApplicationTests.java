package com.laozhang.producer;

import com.laozhang.producer.entity.Order;
import com.laozhang.producer.web.OrderSender;
import com.laozhang.producer.web.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private OrderService orderService;

    @Test
    public void testSend() throws Exception {
        Order order = new Order();
        order.setId("20190413000000001");
        order.setName("测试订单1");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        //orderSender.sendOrder(order);
        orderService.createOrder(order);
    }

}
