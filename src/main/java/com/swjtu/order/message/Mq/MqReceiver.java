package com.swjtu.order.message.Mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接受MQ消息
 * @author 李天峒
 * @date 2019/6/30 15:07
 */
@Component
public class MqReceiver {

    private static final Logger log = LoggerFactory.getLogger(MqReceiver.class);

    //1、@RabbitListener(queues = "myQueue")（需要手动在RabbitMQ界面添加队列）

    //2、自动生成队列 @RabbitListener(queuesToDeclare = @Queue("myQueue"))
//    @RabbitListener(bindings = @QueueBinding(
//            exchange = @Exchange("myExchange"),
//            value = @Queue("myQueue")
//
//    ))
//    public void process(String message){
//        log.info("MqReceiver: {}", message);
//    }

    //3、自动创建，Exchange和Queue进行绑定
    /**
     * 数码供应商 接受消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")

    ))
    public void processComputer(String message){
        log.info("computer MqReceiver: {}", message);
    }

    /**
     * 水果供应商 接受消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitOrder")

    ))
    public void processFruit(String message){
        log.info("fruit MqReceiver: {}", message);
    }
}
