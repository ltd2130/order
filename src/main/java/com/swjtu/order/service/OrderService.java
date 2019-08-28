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
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单（只能卖家操作）
     * @param orderID
     * @return
     */
    OrderDTO finish(String orderID);

}
