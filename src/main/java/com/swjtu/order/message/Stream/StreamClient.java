package com.swjtu.order.message.Stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author 李天峒
 * @date 2019/7/2 21:14
 */
public interface StreamClient {

    String INPUT = "InputMessage";
    String OUTPUT = "OutputMessage";
    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();

    String INPUT2 = "InputMessage2";
    String OUTPUT2 = "OutputMessage2";
    @Input(INPUT2)
    SubscribableChannel input2();
    @Output(OUTPUT2)
    MessageChannel output2();

}
