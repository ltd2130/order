package com.swjtu.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 李天峒
 * @date 2019/4/23 0:10
 */
@RestController
@RequestMapping(value = "/com/swjtu/order")
@Slf4j
public class ClientController {

    /**第二种方式用到的*/
    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg(){

        //1. 第一种方式（直接使用RestTemplate，URL写死）
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8080/product/Msg", String.class);

        //2. 第二种方法（利用loadBalancerClient 动态的获取服务器的ip和端口号，然后再使用RestTemplate）
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s",serviceInstance.getHost(), serviceInstance.getPort()) + "/product/Msg";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
        log.info("【服务地址】 ip={}" , serviceInstance.getHost());
        log.info("【服务端口】 port={}" , serviceInstance.getPort());


        //3. 第三种方式（利用@LoadBalanced，可在restTemplate里使用应用名字代指IP地址和端口号）
//        String response = restTemplate.getForObject("http://PRODUCT/product/Msg", String.class);
//        log.info("response={}", response);

        //4. 第四种方式（使用FeignClient来配置相应的访问接口）
        return response;
    }
}
