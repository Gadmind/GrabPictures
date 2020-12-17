package com.daop.basic.demo.form;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.form
 * @Description: 通用表单
 * @DATE: 2020-11-25
 * @AUTHOR: Administrator
 **/
public abstract class BaseFrom<T> {
    /**
     * 获取实例
     * @return 返回实体类
     */
    public abstract T buildEntity();
}
