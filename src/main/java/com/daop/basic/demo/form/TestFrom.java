package com.daop.basic.demo.form;

import com.daop.basic.demo.entity.Test;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;

/**
 * @BelongsProject: BasicDemo
 * @BelongsPackage: com.daop.basic.demo.form
 * @Description:
 * @DATE: 2020-11-25 22:22
 * @AUTHOR: Daop
 **/
@Data
@ApiModel("测试添加表单数据")
@EqualsAndHashCode(callSuper = false)
public class TestFrom extends BaseFrom<Test> {

    @ApiModelProperty("ID编号")
    @NotEmpty(message = "编号不能为空")
    private Integer testId;

    @ApiModelProperty("测试字段")
    @NotEmpty(message = "不能为空")
    @Length(min = 1, max = 16, message = "长度限制为1~16")
    private String testStr;

    @Override
    public Test buildEntity() {
        Test test = new Test();
        BeanUtils.copyProperties(this, test);
        return test;
    }
}
