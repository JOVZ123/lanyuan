package com.lanyuan.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  //配置类注解
@EnableSwagger2  //开启swagger2的自动配置
public class SwaggerConfig {
    @Bean
    Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) //api基本信息
                .enable(true)  //是否开启 可以单独提取出来
                .select() //查询
                //RequestHandlerselectors,配置要扫描接口的方式
                //basePackage:指定要扫描的包
                //any():扫描全部
                //none():不扫描
                //withClassAnnotation:扫描类上的注解，多数是一个注解的反射对象
                //withMethodAnnotation:扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.lanyuan.springboot.controller"))  //指定的包去做api文档
                .paths(PathSelectors.any())
                .build();

    }
    //配置文档信息
    @Bean
    ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("控制层api文档")
                .description("restFul风格 请求发生")
                .version("1.0")
                .build();
    }
}
