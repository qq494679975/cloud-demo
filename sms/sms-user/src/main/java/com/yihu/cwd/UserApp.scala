package com.yihu.cwd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients

/**
  * Created by Administrator on 2016.10.26.
  */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
class UserApp {

}

object UserApp{
  def main(args: Array[String]) {
    SpringApplication.run(classOf[UserApp])
  }
}
