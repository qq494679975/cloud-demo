package com.yihu.cwd.hadoop.mapReduce;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * Created by Administrator on 2016/4/7.
 */
public class MyWordCount {

    //client 區域
    public static void main(String[] args) throws Exception {
        String uri = "hdfs://192.168.116.132:9000/";
        // Configuration config = new Configuration();
        // 創建job 設置 和配置job
        Job job = new Job();
        // 设置job运行的类
        job.setJarByClass(MyWordCount.class);
        // 设置mapper和reduce类
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReduce.class);
        // 设置需要计算的文件（输入文件）和输出文件的目录
        FileInputFormat.addInputPath(job, new Path(uri
                + "test/20160512.txt"));
        FileOutputFormat.setOutputPath(job, new Path(uri
                + "test/20160512output"));
        // 设置输出key和value的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 提交job等待运行结果 并显示信息
        boolean isSuccess = job.waitForCompletion(true);
        // 结束程序
        System.out.println("結束程序:" + isSuccess);

    }
}
