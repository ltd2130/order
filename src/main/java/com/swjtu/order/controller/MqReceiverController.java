package com.swjtu.order.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 李天峒
 * @date 2019/7/1 22:57
 */
@RestController
public class MqReceiverController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/send")
    public void send(){
        amqpTemplate.convertAndSend("myQueue","now={}"+ new Date());
    }


    @GetMapping("/sendOrder")
    public void sendOrder(){
        amqpTemplate.convertAndSend("myOrder","computer","now={}"+ new Date());
    }
}
