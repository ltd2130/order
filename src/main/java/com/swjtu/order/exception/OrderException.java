package com.swjtu.order.exception;

import com.swjtu.order.enums.ResultEnum;

/**
 * @author 李天峒
 * @date 2019/4/16 23:53
 */
public class OrderException extends RuntimeException{

    private Integer code;

    public OrderException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
