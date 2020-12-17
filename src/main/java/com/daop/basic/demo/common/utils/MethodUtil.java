package com.daop.basic.demo.common.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.common.util
 * @Description: 方法工具类
 * @DATE: 2020-11-25
 * @AUTHOR: Administrator
 **/
@Slf4j
public class MethodUtil {
    /**
     * 私有化工具类 防止被实例化
     */
    private MethodUtil() {
    }

    public static String getLineInfo() {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + " -> " + ste.getLineNumber() + "行";
    }

}
