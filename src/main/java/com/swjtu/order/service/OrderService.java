package com.swjtu.order.service;

import com.swjtu.order.dto.OrderDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 李天峒
 * @date 2019/4/16 22:04
 */
@Component
public interface OrderService {

    /**
     * 创建订单方法
     * @param orderDTO
     * @return
     */
    @Transactional
    OrderDTO create(OrderDTO orderDTO);

}
