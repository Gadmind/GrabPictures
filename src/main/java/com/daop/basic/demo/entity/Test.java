package com.daop.basic.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @BelongsProject: BasicDemo
 * @BelongsPackage: com.daop.basic.demo.entity
 * @Description:
 * @DATE: 2020-11-25 22:24
 * @AUTHOR: Daop
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer testId;

    private String testStr;
}
