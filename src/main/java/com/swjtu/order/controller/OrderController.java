package com.swjtu.order.controller;

import com.swjtu.order.converter.OrderForm2OrderDTO;
import com.swjtu.order.dto.OrderDTO;
import com.swjtu.order.enums.ResultEnum;
import com.swjtu.order.exception.OrderException;
import com.swjtu.order.form.OrderForm;
import com.swjtu.order.service.OrderService;
import com.swjtu.order.utils.ResultVOUtil;
import com.swjtu.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李天峒
 * @date 2019/4/16 22:03
 */
@RestController
@RequestMapping(value = "/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    //1. 表单验证
    //2. 查询商品信息（调用商品信息查询接口）
    //3. 计算总价
    //4. 扣库存
    //5. 订单入库

    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数错误, OrderForm ={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        //orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTO.converter(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }
}
