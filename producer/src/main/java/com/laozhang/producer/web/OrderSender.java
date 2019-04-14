package com.laozhang.producer.web;

import com.laozhang.producer.entity.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderSender {

    public static Map<String, Order> map = new HashMap<>();
    public static Map<String, Integer> map2 = new HashMap<>();
    public static List<Order> list = new ArrayList<>();

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //回调函数：confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData" + correlationData);

            String messageId = correlationData.getId();
            if (ack) {
                // 成功
                map.remove(messageId);
                map2.remove(messageId);
            } else {
                // 失败
                System.out.println("失败：" + messageId);
            }
        }
    };

    public void sendOrder(Order order) throws Exception {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData(order.getMessageId());

        rabbitTemplate.convertAndSend("order-exchange", // exchange
                "order.abcd", // routingKey
                order, // 消息内容
                correlationData); // 消息唯一ID
    }

}
