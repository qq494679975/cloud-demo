package com.yihu.cwd.hadoop.mapReduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


/**
 * reduce 區域
 * 输入ｋｅｙ的类型
 * 输入ｖａｌｕｅ的类型
 * 输出ｋｅｙ的类型
 * 输出ｖａｌｕｅ的类型
 */
public class MyReduce extends Reducer<Text, IntWritable, Text, IntWritable> {

    private int sum = 0;

    private IntWritable value = new IntWritable();

    /**
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException 數據遠程拷貝
     *                              數據按照key排序
     *                              數據處理
     *                              數據格式輸出
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //累加
        for (IntWritable value : values) {
            if (new String(key.getBytes()).equals("1")) {
                sum++;
                System.out.println("叠加:" + sum);
            }
        }
        value.set(sum);
        context.write(new Text("1"), value);
    }
}
