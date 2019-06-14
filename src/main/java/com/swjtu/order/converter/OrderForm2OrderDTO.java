package com.swjtu.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.swjtu.order.dataobject.OrderDetail;
import com.swjtu.order.dto.OrderDTO;
import com.swjtu.order.enums.ResultEnum;
import com.swjtu.order.exception.OrderException;
import com.swjtu.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李天峒
 * @date 2019/4/21 23:59
 */
@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO converter(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenId());
        orderDTO.setBuyerPhone(orderForm.getPhone());

        /** 用来保存订单详情信息*/
        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());

        } catch (Exception e) {
            log.error("【json转换】错误, String={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
