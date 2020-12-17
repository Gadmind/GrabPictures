package com.daop.basic.demo.common.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: BasicDemo
 * @BelongsPackage: com.daop.basic.demo.common.config.mybatis
 * @Description: MyBatis 相关配置
 * @DATE: 2020-11-25 19:42
 * @AUTHOR: Daop
 **/
@Configuration
@MapperScan(value = {"com.daop.basic.demo.mapper"})
public class MyBatisConfiguration {
}
