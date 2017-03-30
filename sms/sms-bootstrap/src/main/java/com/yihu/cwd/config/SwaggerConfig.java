package com.yihu.cwd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by chenweida on 2016/2/3.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
    private static final String PUBLIC_API = "Default";
    private static final String system_API = "System";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket publicAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(PUBLIC_API)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(or(
                        regex("/user/.*")
                ))
                .build()
                .apiInfo(publicApiInfo());
    }

    private ApiInfo publicApiInfo() {
        ApiInfo apiInfo = new ApiInfo("业务API",
                "业务接口。",
                "1.0",
                "No terms of service",
                "cwd@qq.com",
                "The Apache License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );

        return apiInfo;
    }

    @Bean
    public Docket systemAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(system_API)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(or(
                        regex("/menu/.*"),
                        regex("/role/.*")
                ))
                .build()
                .apiInfo(systemApiInfo());
    }

    private ApiInfo systemApiInfo() {
        ApiInfo apiInfo = new ApiInfo("系统模块API",
                "系统模块接口。",
                "1.0",
                "No terms of service",
                "cwd@qq.com",
                "The Apache License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );

        return apiInfo;
    }
}
