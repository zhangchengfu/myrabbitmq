package com.laozhang.producer.config;

import com.laozhang.producer.entity.Order;
import com.laozhang.producer.web.OrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RetryMessageTasker {

    @Autowired
    private OrderSender orderSender;

    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void reSend() throws Exception {
        for (Map.Entry<String, Order> entry : OrderSender.map.entrySet()) {
            Integer count = OrderSender.map2.get(entry.getKey());
            if(count >= 3) {
                // 发送失败
                System.out.println("消息发送失败：" + entry.getKey());
            } else {
                OrderSender.map2.put(entry.getKey(), ++count);
                orderSender.sendOrder(entry.getValue());
            }
        }
    }
}
