package com.daop.basic.demo.common.config.swagger;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.config.swagger
 * @Description: Swagger 配置类 访问地址：项目地址+/swagger-ui/index.html
 * @DATE: 2020-11-25
 * @AUTHOR: Daop
 **/
@Configuration
@EnableOpenApi
@AllArgsConstructor
@Profile({"dev", "test"})
@EnableConfigurationProperties(SwaggerInfo.class)
public class SwaggerConfiguration {
    private SwaggerInfo swaggerInfo;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerInfo.getGroupName())
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(false)
                .forCodeGeneration(true)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerInfo.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 设置 API 信息
     *
     * @return 返回 ApiInfo
     */
    private ApiInfo apiInfo() {
        StringVendorExtension vendorExtension = new StringVendorExtension("TT", "VV");
        Collection<VendorExtension> vendorExtensions = new ArrayList<>();
        vendorExtensions.add(vendorExtension);
        Contact contact = new Contact("name", "url", "email");

        return new ApiInfo(
                swaggerInfo.getTitle(),
                swaggerInfo.getDescription(),
                swaggerInfo.getVersion(),
                "termsOfServiceUrl", contact, "License", "LicenseUrl",
                vendorExtensions);
    }
}
