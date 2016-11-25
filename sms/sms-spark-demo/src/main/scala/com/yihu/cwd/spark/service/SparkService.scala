package com.yihu.cwd.spark.service

import com.yihu.cwd.spark.util.SparkUtil
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.StreamingContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by Administrator on 2016/11/24.
  */
@Service
class SparkService {
  @Autowired
  private var sparkUtil: SparkUtil = _

  @throws(classOf[Exception])
  def addJob(jobName: String) = {
    //得到spark上下文
    var sc = sparkUtil.getSpackContent(jobName);
    //从hdfs得到文件
    val textFile = sc.textFile("hdfs://192.168.131.240:9000/test/20160513.txt")
    //计算文件单词数
    var aa = textFile.filter(line => line.contains("spark")).count()
    //输出单词数
    println("文件单词总数 spark:%s ", aa)
  }

}
