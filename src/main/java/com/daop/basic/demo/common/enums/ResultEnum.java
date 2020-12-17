package com.daop.basic.demo.common.enums;

import lombok.Getter;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.common.enums
 * @Description: 返回状态枚举类
 * @DATE: 2020-11-25
 * @AUTHOR: Daop
 **/
@Getter
public enum ResultEnum {
    /**
     * 未知异常
     */
    UNKNOWN_EXCEPTION(100, "未知异常"),
    /**
     * 参数格式错误
     */
    FORMAT_ERROR(101, "参数格式错误"),
    /**
     * 超时
     */
    TIME_OUT(102, "超时"),
    /**
     * 添加失败
     */
    ADD_ERROR(102, "添加失败"),
    /**
     * 更新失败
     */
    UPDATE_ERROR(102, "更新失败"),
    /**
     * 删除失败
     */
    DELETE_ERROR(102, "删除失败"),
    /**
     * 查询失败
     */
    GET_ERROR(102, "查询失败"),
    /**
     *
     */
    ARGUMENT_TYPE_MISMATCH(102, "参数类型不匹配"),
    /**
     *
     */
    REQ_METHOD_NOT_SUPPORT(102, "请求方式错误"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过状态码获取枚举对象
     *
     * @param code 状态码
     * @return 枚举对象
     */
    public static ResultEnum getByCode(int code) {
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (code == resultEnum.getCode()) {
                return resultEnum;
            }
        }
        return null;
    }
}
