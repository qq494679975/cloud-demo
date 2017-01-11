package com.cwd.sparkstreaming

import java.util.UUID

import com.cwd.util.HBaseUtil
import kafka.serializer.StringDecoder
import net.sf.json.JSONObject
import org.apache.hadoop.hbase.{HColumnDescriptor, HTableDescriptor, TableName, HBaseConfiguration}
import org.apache.hadoop.hbase.client._
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}

import scala.collection.JavaConversions
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 2016/12/19.
  * 1.通过创建输入DStreams来定义输入源
  * 2.通过对DStreams应用转变操作和输出操作来定义流计算。
  * 3.用streamingContext.start()来开始接收数据和处理流程。
  * 4.通过streamingContext.awaitTermination()方法来等待处理结束（手动结束或因为错误）
  * 5.还可以通过streamingContext.stop()来手动结束进程。
  */
object KafkaStreaming {
  // 16-12-21:11:33:31.453
  // INFO
  // com.cwd.service.LogService
  // (LogService.scala:16)
  // {input:{params:asd},output:{params:sss},status:1,logId:aasdasdasd,module:user}
  final val coloums = ArrayBuffer("createDate", "logLevel", "class", "logIndex", "message")

  def main(args: Array[String]) {
    if (args.length < 4) {
      System.err.println("Usage: KafkaStreaming <zkQuorum> <group> <topics> <numThreads>")
      System.exit(1)
    }
    //初始化spark上下文
    val Array(zkQuorum, group, topics, numThreads) = args
    val sparkConf = new SparkConf().setAppName("logToHBase").setMaster("local[2]")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    ssc.checkpoint("checkpoint")
    //设置主题
    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
    val tableName = "flumeLog";
    val tableNameFamily = "flumeLogFamily";
    val hbaseUtil = new HBaseUtil()
    //表不存在创建表
    if (!hbaseUtil.isTableExists(tableName)) {
      hbaseUtil.createTable(tableName, tableNameFamily)
    }
    //从kafka流得到数据  取出value
    val lineArray = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
    val count = lineArray.count()
    print(count)
    lineArray.foreachRDD { rdd =>
      rdd.foreachPartition { partitionOfRecords =>
        var data = partitionOfRecords.toString().split(" ")
        //添加数据
        hbaseUtil.insertRecord(tableName, UUID.randomUUID().toString, tableNameFamily, JavaConversions.asJavaCollection(coloums).toArray, JavaConversions.asJavaCollection(data).toArray)
      }
    }

    ssc.start() // 开始计算
    ssc.awaitTermination() // 等待计算终止
  }
}
