package com.swjtu.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * 获取商品信息
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(){
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
     * @return
     */
    private String fallback(){
        return "太拥挤了，请稍候再试";
    }

    private String defaultFallback(){
        return "默认提示 太拥挤了，请稍后再试";
    }
}
