package com.yihu.cwd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.scheduling.annotation.EnableScheduling

/**
  * Created by Administrator on 2016.10.25.
  */
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@EnableZuulProxy
@EnableScheduling
@SpringBootApplication
class GateWayApp {

}

object GateWayApp{
  def main(args: Array[String]) {
    SpringApplication.run(classOf[GateWayApp])
  }
}