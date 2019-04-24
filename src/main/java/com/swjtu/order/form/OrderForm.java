package com.swjtu.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 李天峒
 * @date 2019/4/16 23:48
 */
@Data
public class OrderForm {


    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    private String address;

    /**
     * 买家微信号
     */
    @NotEmpty(message = "微信号必填")
    private String openId;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
