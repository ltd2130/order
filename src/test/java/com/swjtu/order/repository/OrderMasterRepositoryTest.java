package com.swjtu.order.repository;

import com.swjtu.order.dataobject.OrderMaster;
import com.swjtu.order.enums.OrderStatusEnum;
import com.swjtu.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author 李天峒
 * @date 2019/4/12 22:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("燕子李三");
        orderMaster.setBuyerPhone("1101101101111110");
        orderMaster.setBuyerAddress("中国古代");
        orderMaster.setBuyerOpenid("飞檐走壁");
        orderMaster.setOrderAmount(new BigDecimal(12));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster result =  repository.save(orderMaster);
        Assert.assertNotNull(result);
    }
}