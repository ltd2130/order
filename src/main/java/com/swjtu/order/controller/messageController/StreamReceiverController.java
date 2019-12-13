package com.swjtu.order.controller.messageController;

import com.alibaba.fastjson.JSONObject;
import com.swjtu.order.dto.OrderDTO;
import com.swjtu.order.message.Stream.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李天峒
 * @date 2019/7/2 21:26
 */
@RestController
public class StreamReceiverController {

    @Autowired
    private StreamClient streamClient;

    /**
     * 发送消息到通道
     */
    @GetMapping("/sendMessage")
    public void send() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123456");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());

        JSONObject object = new JSONObject();
        object.put("id",123);
        object.put("name","litiantong");
        object.put("age",24);
        streamClient.input().send(MessageBuilder.withPayload(object).build());
    }
}
