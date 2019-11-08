package com.swjtu.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author 李天峒
 * @date 2019/8/22 19:07
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {


//    超时配置
//    @HystrixCommand(commandProperties = {            // 设置超时时间，在product服务里面此接口睡眠2s，如果不设置时间，默认服务降级时间为1s，会发生降级
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")      //这里设置超时时间为3s，即认为是超时
//    })

//    熔断配置
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),                                 //Circuit Breaker:断路器
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),                   //滚动窗口中，设置断路器最小请求数
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),             //断路器的打开和关闭时间（open、halfOpen）
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")                  //设置断路器打开的最小请求错误比（即超过60%的错误请求便打开断路器）
//    })

    /**
     * 获取商品信息
     *
     * @return
     */
    @HystrixCommand()
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject("http://127.0.0.1:8084/product/listForOrder",
                Arrays.asList("10001"),
                String.class
        );
        return response;
        /** 此异常可以表明，哪怕不访问其他项目，我本身抛出一个异常，也可以出发服务降级
         *  在这连接数如果过高的时候，可以依据连接数的个数来不定时或者不定点的抛出异常
         *  完成服务的降级*/
//        throw new RuntimeException("发生异常了");
    }

    /**
     * 服务降级方法，当上面的请求请求不到的时候，触发这个服务
     *
     * @return
     */
    private String fallback() {
        return "太拥挤了，请稍候再试";
    }

    private String defaultFallback() {
        return "默认提示 太拥挤了，请稍后再试";
    }
}
