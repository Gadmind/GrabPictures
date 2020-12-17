package com.daop.basic.demo.common.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.config.swagger
 * @Description: Swagger 配置信息
 * @DATE: 2020-11-25
 * @AUTHOR: Daop
 **/

@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerInfo {
    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String description;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 版本
     */
    private String version;

    /**
     * 扫描的包路径
     */
    private String basePackage;

    /**
     * 接口作者
     */
    private String author;
}
