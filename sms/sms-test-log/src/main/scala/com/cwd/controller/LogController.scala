package com.cwd.controller

import com.cwd.service.LogService
import io.swagger.annotations.{ApiOperation, Api}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMethod, RequestMapping, RestController}

/**
  * Created by Administrator on 2016/12/18.
  */
@RestController
@RequestMapping(value = Array("/log"))
@Api(description = "日志")
class LogController {
  @Autowired
  var logservice: LogService = _


  @RequestMapping(value = Array("/produceLog"), method = Array(RequestMethod.GET))
  def produceLog(): Unit = {
    logservice.produceLog();
  }
}
