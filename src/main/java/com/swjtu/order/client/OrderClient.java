package com.swjtu.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author 李天峒
 * @date 2019/6/3 14:34
 */
@FeignClient(value = "product")
public interface OrderClient {

    /**
     * 获取product服务的接口返回值
     * @return
     */
    @GetMapping("/product/Msg")
    String getProductMsg();
}
