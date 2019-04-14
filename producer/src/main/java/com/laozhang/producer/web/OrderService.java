package com.laozhang.producer.web;

import com.laozhang.producer.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderSender orderSender;

    public void createOrder(Order order) throws Exception {
        OrderSender.map.put(order.getMessageId(),order);
        OrderSender.map2.put(order.getMessageId(),0);
        orderSender.sendOrder(order);
    }
}
