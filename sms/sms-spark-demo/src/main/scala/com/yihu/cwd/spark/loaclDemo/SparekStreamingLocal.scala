//package com.yihu.cwd.spark.loaclDemo
//
//import java.util._
//import scala.collection.JavaConversions._
//import org.apache.kafka.clients.consumer.{KafkaConsumer, ConsumerRecord, ConsumerRecords}
//
///**
//  * Created by Administrator on 2016/11/28.
//  */
//class SparekStreamingLocal {
//  def cunsumer() {
//    var props: Properties = new Properties
//    props.put("bootstrap.servers", "192.168.59.240:9092")
//    //消费者的组id
//    props.put("group.id", "testlog")
//    props.put("enable.auto.commit", "true")
//    props.put("auto.commit.interval.ms", "1000")
//    //从poll(拉)的回话处理时长
//    props.put("session.timeout.ms", "30000")
//    //poll的数量限制
//    //props.put("max.poll.records", "100");
//    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
//    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
//    var myconsumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
//    //订阅主题列表topic
//    myconsumer.subscribe(Arrays.asList("foo"))
//
//    while (true) {
//      org.apache.kafka.clients.NetworkClient
//      var records: ConsumerRecords[String, String] = myconsumer.poll(100)
//      for (record <- records) {
//        printf("offset = %d, key = %s, value = %s  ,topic =%s", record.offset, record.key, record.value, record.topic() + "\n")
//      }
//    }
//  }
//}
//
//object MyKafkaConsumer {
//  def main(args: Array[String]) {
//    var myKafkaConsumer: SparekStreamingLocal = new SparekStreamingLocal();
//    myKafkaConsumer.cunsumer();
//  }
//}