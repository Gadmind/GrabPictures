package com.daop.basic.demo.controller;

import com.daop.basic.demo.common.utils.ResultVoUtil;
import com.daop.basic.demo.entity.Test;
import com.daop.basic.demo.form.TestFrom;
import com.daop.basic.demo.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.controller
 * @Description: 测试接口
 * @DATE: 2020-11-25
 * @AUTHOR: Administrator
 **/
@RestController
@Api(tags = "测试接口")
public class TestController {
    private static final String BASE_URL = "/test";

    /**
     * 查询操作
     *
     * @return
     */
    @GetMapping(BASE_URL + "s")
    @ApiResponses(
            @ApiResponse(code = 200, message = "操作成功", response = Test.class)
    )
    public ResultVO list() {
        return ResultVoUtil.success("This is select Method.");
    }

    @PostMapping(BASE_URL)
    public ResultVO add(@RequestBody TestFrom testFrom) {
        return ResultVoUtil.success("This is select Method.");
    }

    @DeleteMapping(BASE_URL + "/{param}")
    public ResultVO delete(@PathVariable("param") String param) {
        return ResultVoUtil.success("This is select Method.");
    }

    @PutMapping(BASE_URL)
    public ResultVO update() {
        return ResultVoUtil.success("This is select Method.");
    }
}