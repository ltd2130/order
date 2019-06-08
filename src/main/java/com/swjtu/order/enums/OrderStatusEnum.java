package com.swjtu.order.enums;

/**
 * @author 李天峒
 * @date 2019/4/12 23:06
 */
public enum OrderStatusEnum {
    /** 订单状态*/
    NEW(0,"未支付"),
    FINISH(1,"已支付"),
    CANCLE(2,"已取消");

    private Integer code;

    private String message;


    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
