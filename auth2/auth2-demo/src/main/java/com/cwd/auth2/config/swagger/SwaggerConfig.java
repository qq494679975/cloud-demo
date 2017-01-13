package com.cwd.auth2.config.swagger;

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
 * swagger-ui的配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
    private static final String Doctor_API = "Doctor_API";
    private static final String Patient_API = "Patient_API";
    private static final String Common_API = "Common_API";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket commonAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(Common_API)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(or(
                        regex("/*/.*"),
                        regex("/login/.*"),
                        regex("/oauth/.*"),
                        regex("/user/.*")
                ))
                .build()
                .apiInfo(commonApiInfo());
    }

//    @Bean
//    public Docket doctorAPI() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName(Doctor_API)
//                .genericModelSubstitutes(DeferredResult.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/")
//                .select()
//                .paths(or(
//                        regex("/doctor/.*")
//                ))
//                .build()
//                .apiInfo(doctorApiInfo());
//    }
//
//    @Bean
//    public Docket patientAPI() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName(Patient_API)
//                .genericModelSubstitutes(DeferredResult.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/")
//                .select()
//                .paths(or(
//                        regex("/patient/.*")
//                ))
//                .build()
//                .apiInfo(patientApiInfo());
//    }


    //    private ApiInfo doctorApiInfo() {
//        ApiInfo apiInfo = new ApiInfo("杨大夫 rest API",
//                "杨大夫医生端",
//                "1.0",
//                "No terms of service",
//                "admin@jkzl.com",
//                "The Apache License, Version 2.0",
//                "http://www.apache.org/licenses/LICENSE-2.0.html"
//        );
//        return apiInfo;
//    }
//    private ApiInfo patientApiInfo() {
//        ApiInfo apiInfo = new ApiInfo("杨大夫 rest API",
//                "杨大夫患者端",
//                "1.0",
//                "No terms of service",
//                "admin@jkzl.com",
//                "The Apache License, Version 2.0",
//                "http://www.apache.org/licenses/LICENSE-2.0.html"
//        );
//        return apiInfo;
//    }
    private ApiInfo commonApiInfo() {
        ApiInfo apiInfo = new ApiInfo("杨大夫 rest API",
                "杨大夫公共",
                "1.0",
                "No terms of service",
                "admin@jkzl.com",
                "The Apache License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );
        return apiInfo;
    }
}
