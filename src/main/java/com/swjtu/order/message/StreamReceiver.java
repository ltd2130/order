package com.swjtu.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author 李天峒
 * @date 2019/7/2 21:22
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {


    /**
     * 接受Input通道的消息
     * @param message
     */
    @StreamListener(StreamClient.INPUT)
    public void processInput(Object message){
        log.info("StreamReceiver_Input:{}",message);
    }

    /**
     * 接受Output通道的消息
     * @param message
     */
    @StreamListener(StreamClient.OUTPUT)
    @SendTo(StreamClient.OUTPUT2)
    public String processOutput(Object message){
        log.info("StreamReceiver_Output:{}",message);
        return "received";
    }

    /**
     * 回复确认消息
     * @param message
     */
    @StreamListener(StreamClient.OUTPUT2)
    public void processOutput2(Object message){
        log.info("StreamReceiver_Output2:{}",message);
    }
}
