package com.cwd.service

import org.slf4j.{LoggerFactory, Logger}
import org.springframework.stereotype.Service

/**
  * Created by Administrator on 2016/12/18.
  */
@Service
class LogService {

  private var logger: Logger = LoggerFactory.getLogger(classOf[LogService])
  //private var logger: Logger = LoggerFactory.getLogger("test")

  def produceLog(): Unit = {
    logger.info("{input:{params:asd},output:{params:sss},status:1,logId:aasdasdasd,module:user}")
  }


}
