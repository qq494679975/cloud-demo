package com.cwd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * Created by Administrator on 2016/12/18.
  */
@SpringBootApplication
class Testlog {

}

object Testlog {
  def main(args: Array[String]) {
    SpringApplication.run(classOf[Testlog])
  }
}
