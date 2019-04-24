package com.swjtu.order.utils;

import com.swjtu.order.vo.ResultVO;
import lombok.Data;

/**
 * @author 李天峒
 * @date 2019/4/11 23:28
 */
@Data
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        return ResultVOUtil.success(null);
    }

    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        resultVO.setData(null);
        return  resultVO;
    }
}
