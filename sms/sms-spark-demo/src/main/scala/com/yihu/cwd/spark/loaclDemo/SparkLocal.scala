package com.yihu.cwd.spark.loaclDemo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/11/28.
  */
object SparkLocal {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("test-spark-local").setMaster("spark://192.168.59.240:7077");
    //sparkConf.setMaster("spark://192.168.59.240:7077")
    val sc = new SparkContext(sparkConf)
    //得到spark上下文
    //从hdfs得到文件
    val textFile = sc.textFile("hdfs://192.168.59.240:9000/user/hadoop/hadoop/test.log")
    textFile.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).collect().foreach(println)
    sc.stop()
  }
}
