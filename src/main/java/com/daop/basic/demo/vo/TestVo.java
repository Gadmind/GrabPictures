package com.daop.basic.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: BasicDemo
 * @BelongsPackage: com.daop.basic.demo.vo
 * @Description: 测试视图模型
 * @DATE: 2020-11-25 22:34
 * @AUTHOR: Daop
 **/
@Data
@ApiModel("测试视图模型")
public class TestVo {
    @ApiModelProperty("测试编号")
    private Integer id;

    @ApiModelProperty("测试字段")
    private String testStr;
}
