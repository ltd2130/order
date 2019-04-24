package com.swjtu.order.repository;

import com.swjtu.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 李天峒
 * @date 2019/4/12 18:27
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
