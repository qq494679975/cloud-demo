package com.yihu.cwd.spark.util

import org.apache.spark.{SparkContext, SparkConf}
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
  * Created by Administrator on 2016/11/24.
  *
  * setMaster
  * local 本地单线程
  * local[K] 本地多线程（指定K个内核）
  * local[*] 本地多线程（指定所有可用内核）
  * spark://HOST:PORT 连接到指定的 Spark standalone cluster master，需要指定端口。
  * mesos://HOST:PORT 连接到指定的 Mesos 集群，需要指定端口。
  * yarn-client客户端模式 连接到 YARN 集群。需要配置 HADOOP_CONF_DIR。
  * yarn-cluster集群模式 连接到 YARN 集群 。需要配置 HADOOP_CONF_DIR。
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
    val sparkConf = new SparkConf().setAppName(jobName).setMaster("spark://192.168.59.240:7077");
    //sparkConf.setMaster("spark://192.168.59.240:7077")
    //sparkConf.setMaster("local")
    sparkConf.set("spark.testing.memory", "2147480000")
    //sparkConf.setExecutorEnv(Array(("spark.deploy.mode", "client")));
    val sc = new SparkContext(sparkConf)
    return sc
  }
}
