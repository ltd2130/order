package com.swjtu.order.repository;

import com.swjtu.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 李天峒
 * @date 2019/4/12 18:28
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
