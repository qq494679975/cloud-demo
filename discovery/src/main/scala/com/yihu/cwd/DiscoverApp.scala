package com.yihu.cwd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

/**
  * Created by Administrator on 2016.10.24.
  */
@SpringBootApplication
@EnableEurekaServer
class DiscoverApp {

}

object DiscoverApp{
  def main(args: Array[String]) {
    SpringApplication.run(classOf[DiscoverApp])
  }
}
