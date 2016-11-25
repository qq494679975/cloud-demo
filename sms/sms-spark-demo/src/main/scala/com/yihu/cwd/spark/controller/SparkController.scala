package com.yihu.cwd.spark.controller

import com.yihu.cwd.spark.service.SparkService
import io.swagger.annotations.{ApiParam, ApiOperation, Api}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestParam, RequestMethod, RequestMapping, RestController}

/**
  * Created by Administrator on 2016/11/24.
  */
@RestController
@RequestMapping(value = Array("/spark"))
@Api(description = "spark-demo相关的接口")
class SparkController {

  @Autowired
  private var sparkService: SparkService = _

  @RequestMapping(value = Array("/addJob"), method = Array(RequestMethod.GET))
  @ApiOperation(value = "添加spark任务")
  def addJob(@ApiParam(name = "jobName", value = "任务名称") @RequestParam(value = "jobName", required = true) jobName: String) = {
    try {
      sparkService.addJob(jobName);
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}
