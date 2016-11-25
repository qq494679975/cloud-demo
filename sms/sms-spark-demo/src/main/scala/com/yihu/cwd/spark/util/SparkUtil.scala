package com.yihu.cwd.spark.util

import org.apache.spark.{SparkContext, SparkConf}
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
  * Created by Administrator on 2016/11/24.
  */
@Component
class SparkUtil {
  @Value("${spark.address}")
  var sparkAddr: String = _

  /**
    * 初始化spark上下文
    *
    * @param jobName job名称
    * @return
    */
  def getSpackContent(jobName: String): SparkContext = {
    val sparkConf = new SparkConf().setAppName(jobName).setMaster("yarn-client");
    val sc = new SparkContext(sparkConf)
    return sc
  }
}
