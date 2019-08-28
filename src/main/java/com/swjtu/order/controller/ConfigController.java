package com.swjtu.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李天峒
 * @date 2019/6/15 0:17
 */
@RestController
@RefreshScope
public class ConfigController {
    @Value("${env}")
    private String env;

    @GetMapping("/env")
    public String getEnv(){
        return env;
    }
}
