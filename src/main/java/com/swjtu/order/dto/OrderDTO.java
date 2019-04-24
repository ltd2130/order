package com.swjtu.order.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.swjtu.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 李天峒
 * @date 2019/4/16 22:05
 */
@Data
public class OrderDTO {


    /**
     * 订单ID.
     */
    private String orderId;
    /**
     * 买家姓名
     */
    private String buyerName;
    /**
     * 买家电话
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信ID
     */
    private String buyerOpenid;
    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态（默认0新下单）
     */
    private Integer orderStatus;
    /**
     * 支付状态(默认0未支付)
     */
    private Integer payStatus;

    /**
     * 订单对应的商品对象
     */
    List<OrderDetail> orderDetailList;

}
