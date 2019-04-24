package com.swjtu.order.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 李天峒
 * @date 2019/4/12 18:21
 */
@Entity
@Data
@DynamicUpdate(true)
public class OrderMaster {

    /**
     * 订单ID.
     */
    @Id
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
    private Integer orderStatus = 0;
    /**
     * 支付状态(默认0未支付)
     */
    private Integer payStatus = 0;
}
