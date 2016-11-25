package com.yihu.cwd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * Created by Administrator on 2016.10.26.
  */
@SpringBootApplication
class SparkApp {

}

object SparkApp{
  def main(args: Array[String]) {
    SpringApplication.run(classOf[SparkApp])
  }
}
