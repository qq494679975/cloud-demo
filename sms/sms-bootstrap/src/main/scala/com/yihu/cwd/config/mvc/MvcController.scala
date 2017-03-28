package com.yihu.cwd.config.mvc

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, ResourceHandlerRegistry, WebMvcConfigurerAdapter}

/**
  * Created by chenweida on 2017/3/28.
  */
@Configuration
@EnableWebMvc
class MvcController extends WebMvcConfigurerAdapter {

  //会在默认的基础上增加/bootstrap/**映射到classpath:/bootstrap/，
  // 不会影响默认的方式，可以同时使用。
  override def addResourceHandlers(registry: ResourceHandlerRegistry) {
    registry
      .addResourceHandler("/**")
      .addResourceLocations("classpath:/webapp/html/")

    registry
      .addResourceHandler("/demo/**")
      .addResourceLocations("classpath:/webapp/hplus/")


    registry
      .addResourceHandler("/common/**")
      .addResourceLocations("classpath:/webapp/common/")
  }
}
