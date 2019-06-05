package com.swjtu.order.service;

import com.swjtu.order.dto.OrderDTO;
import org.springframework.stereotype.Component;

/**
 * @author 李天峒
 * @date 2019/4/16 22:04
 */
@Component
public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);

    void test ();
}
