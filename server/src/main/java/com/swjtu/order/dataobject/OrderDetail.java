package com.swjtu.order.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 李天峒
 * @date 2019/4/12 18:22
 */
@Data
@Entity
@DynamicUpdate
public class OrderDetail {

    /**
     * 订单详情ID
     */
    @Id
    private String detailId;
    /**
     * 订单ID.
     */
    private String orderId;
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 商品数量
     */
    private Integer productQuantity;
    /**
     * 商品小图
     */
    private String productIcon;

   /* *//**
     * 创建时间
     *//*
    private Date createTime;
    *//**
     * 更新时间
     *//*
    private Date updateTime;*/
}
