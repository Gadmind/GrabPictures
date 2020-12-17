package com.daop.basic.demo.common.utils;

import com.daop.basic.demo.common.enums.ResultEnum;
import com.daop.basic.demo.vo.ResultVO;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.common.util
 * @Description: 返回数据工具类
 * @DATE: 2020-11-25
 * @AUTHOR: Daop
 **/
public class ResultVoUtil {
    /**
     * 私有化工具类 防止被实例化
     */
    private ResultVoUtil() {
    }

    /**
     * 成功
     *
     * @param obj 需要返回的数据
     * @return data
     */
    public static ResultVO success(Object obj) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("ok");
        resultVO.setData(obj);
        return resultVO;
    }

    /**
     * 成功
     *
     * @return 返回空
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 错误
     *
     * @param resultEnum 错误枚举类
     * @return 错误信息
     */
    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO result = new ResultVO();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMsg());
        return result;
    }

    /**
     * 错误
     *
     * @param code 状态码
     * @param msg  消息
     * @return ResultBean
     */
    public static ResultVO error(Integer code, String msg) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    /**
     * 错误
     *
     * @param msg 错误信息
     * @return ResultBean
     */
    public static ResultVO error(String msg) {
        return error(-1, msg);
    }
}
