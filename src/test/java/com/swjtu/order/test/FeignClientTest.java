package com.swjtu.order.test;

import com.swjtu.order.client.OrderClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 李天峒
 * @date 2019/7/25 9:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FeignClientTest {

    /*@Autowired
    private OrderClient orderClient;*/

    @Test
    public void feignClientTest(){
        /*String msg = orderClient.getProductMsg();
        log.info("【msg】={}",msg);*/
    }
}
