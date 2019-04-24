package com.swjtu.order.service.impl;

import com.swjtu.order.dataobject.OrderMaster;
import com.swjtu.order.dto.OrderDTO;
import com.swjtu.order.enums.OrderStatusEnum;
import com.swjtu.order.enums.PayStatusEnum;
import com.swjtu.order.repository.OrderDetailRepository;
import com.swjtu.order.repository.OrderMasterRepository;
import com.swjtu.order.service.OrderService;
import com.swjtu.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author 李天峒
 * @date 2019/4/16 22:12
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        //TODO 查询商品信息（调用商品信息查询接口）

        //TODO 计算总价

        //TODO 扣库存

        //TODO 订单入库

        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
