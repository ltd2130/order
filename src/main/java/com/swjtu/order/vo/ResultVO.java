package com.swjtu.order.vo;

import lombok.Data;

/**
 * @author 李天峒
 * http请求返回的前端数据
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回内容
     */
    private T Data;
}
