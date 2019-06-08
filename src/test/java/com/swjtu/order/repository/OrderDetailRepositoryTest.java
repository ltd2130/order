package com.swjtu.order.repository;

import com.swjtu.order.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author 李天峒
 * @date 2019/4/16 21:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    
    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("113");
        orderDetail.setOrderId("123457");
        orderDetail.setProductId("2222");
        orderDetail.setProductName("哇哈哈");
        orderDetail.setProductPrice(new BigDecimal(2.5));
        orderDetail.setProductQuantity(10);
        orderDetail.setProductIcon("http://xxx");
        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }
}