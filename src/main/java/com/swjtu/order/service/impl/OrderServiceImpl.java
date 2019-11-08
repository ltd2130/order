package com.swjtu.order.service.impl;

import com.swjtu.order.dataobject.OrderDetail;
import com.swjtu.order.dataobject.OrderMaster;
import com.swjtu.order.dto.OrderDTO;
import com.swjtu.order.enums.OrderStatusEnum;
import com.swjtu.order.enums.PayStatusEnum;
import com.swjtu.order.enums.ResultEnum;
import com.swjtu.order.exception.OrderException;
import com.swjtu.order.repository.OrderDetailRepository;
import com.swjtu.order.repository.OrderMasterRepository;
import com.swjtu.order.service.OrderService;
import com.swjtu.order.utils.KeyUtil;
import com.swjtu.product.client.ProductClient;
import com.swjtu.product.client.utils.ResultVO;
import com.swjtu.product.common.DecreaseStockInput;
import com.swjtu.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 李天峒
 * @date 2019/4/16 22:12
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        String detailId = "";
        //查询商品信息（调用商品信息查询接口）
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.getListForOrder(productIdList);

        //计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
                for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
                    for (ProductInfoOutput productInfo : productInfoList) {
                        if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                            //单价*数量
                            orderAmount = productInfo.getProductPrice()
                                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                                    .add(orderAmount);
                            BeanUtils.copyProperties(productInfo, orderDetail);
                            orderDetail.setOrderId(orderId);
                            detailId = KeyUtil.genUniqueKey();
                            orderDetail.setDetailId(detailId);
                            //订单详情入库
                            orderDetailRepository.save(orderDetail);
                            log.info("【详单入库成功】detailId={}", detailId);
                        }
            }
        }
        //扣库存（调用商品服务）
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

        ResultVO resultVO = productClient.decreaseStock(decreaseStockInputList);
        if (resultVO.getCode() != 200) {
            throw new OrderException(resultVO.getCode(), resultVO.getMessage());
        }
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        log.info("【订单入库成功】orderId={}", orderId);
        return orderDTO;
    }

    /**
     * 完结订单（只能卖家操作）
     *
     * @param orderID
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finish(String orderID) {

        //1. 查询订单是否存在
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderID);
        if (!orderMasterOptional.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2. 判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // 3. 修改订单状态为完结
        orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        orderMasterRepository.save(orderMaster);

        //4. 查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderID);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
