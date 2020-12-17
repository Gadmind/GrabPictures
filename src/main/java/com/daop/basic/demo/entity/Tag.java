package com.daop.basic.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.entity
 * @Description: 标签实体类
 * @DATE: 2020-12-15
 * @AUTHOR: Administrator
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Tag implements Serializable {
    /**
     * 标签ID
     */
    @TableId(type = IdType.AUTO)
    long tagId;
    /**
     * 标签名称
     */
    String tagName;
    /**
     * 标签链接
     */
    String tagRef;

    public Tag tagId(long tagId) {
        this.tagId = tagId;
        return this;
    }

    public Tag tagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    public Tag tagRef(String tagRef) {
        this.tagRef = tagRef;
        return this;
    }
}
