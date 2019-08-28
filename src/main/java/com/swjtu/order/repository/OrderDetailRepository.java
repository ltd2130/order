package com.swjtu.order.repository;

import com.swjtu.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 李天峒
 * @date 2019/4/12 18:28
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 一句orderId查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
