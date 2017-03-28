package com.yihu.cwd.config.swagger

import com.google.common.base.Predicates._
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.context.request.async.DeferredResult
import org.springframework.web.servlet.config.annotation.{ResourceHandlerRegistry, WebMvcConfigurerAdapter}
import springfox.documentation.builders.PathSelectors._
import springfox.documentation.service.{ApiInfo, Contact}
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
  * Created by Administrator on 2016.10.25.
  */
@Configuration
@EnableSwagger2
class SwaggerConfigSwaggerConfig extends WebMvcConfigurerAdapter{
  private val PUBLIC_API: String = "Default"

  override def addResourceHandlers(registry: ResourceHandlerRegistry) {
    registry.addResourceHandler("swagger.html").addResourceLocations("classpath:/META-INF/resources/")
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
  }

  @Bean def publicAPI: Docket = {
     new Docket(
      DocumentationType.SWAGGER_2).
      groupName(PUBLIC_API).
      genericModelSubstitutes(classOf[DeferredResult[_]]).
      useDefaultResponseMessages(false).
      forCodeGeneration(true).
      pathMapping("/").
      select.
      paths(or(regex("/test/.*"))).
      build.
      apiInfo(publicApiInfo)
  }

  private def publicApiInfo: ApiInfo = {
    var apiInfo: ApiInfo = new ApiInfo("spring-cloud",
      "spring-cloud。",
      "1.0",
      "No terms of service",
      new Contact("cwd","","494679975@qq.com"),
      "The Apache License, Version 2.0",
      "http://www.apache.org/licenses/LICENSE-2.0.html")
    return apiInfo
  }
}
