package com.yihu.cwd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.config.server.EnableConfigServer

/**
  * Created by Administrator on 2016.10.24.
  */
@SpringBootApplication
@EnableConfigServer // 启动配置服务
@EnableDiscoveryClient //开启注册到发现服务
class ConfigApp {
}

object  ConfigApp{
  def main(args: Array[String]) {
    SpringApplication.run(classOf[ConfigApp])
  }
}
