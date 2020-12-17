package com.daop.basic.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.vo
 * @Description: 固定返回格式
 * @DATE: 2020-11-25
 * @AUTHOR: Daop
 **/
@Data
@ApiModel("固定返回格式")
public class ResultVO {
    /**
     * 响应码
     */
    @ApiModelProperty("响应码")
    private Integer code;
    /**
     * 响应信息
     */
    @ApiModelProperty("响应信息")
    private String message;
    /**
     * 响应数据
     */
    @ApiModelProperty("响应数据")
    private Object data;
}
