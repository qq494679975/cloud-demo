package com.yihu.cwd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * Created by chenweida on 2017/3/28.
  */
@SpringBootApplication
class App {

}

object App {
  def main(args: Array[String]) {
    SpringApplication.run(classOf[App])
  }
}

