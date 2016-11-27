package com.yihu.cwd.hadoop.mapReduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * mapper 區域
 * 输入ｋｅｙ的类型
 * 输入ｖａｌｕｅ的类型
 * 输出ｋｅｙ的类型
 * 输出ｖａｌｕｅ的类型
 * 数字是LongWritable
 * String是text
 */
public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text word = new Text();
    private final IntWritable one = new IntWritable();

    /**
     * @param key
     * @param value   一行的值
     * @param context
     * @throws IOException
     * @throws InterruptedException 輸入數據格式解析
     *                              輸入數據處理
     *                              數據分組 （map處理之後的結果數據）
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取每行数据的值
        String lineValue = value.toString();
        //把每行的數據分割
        StringTokenizer st = new StringTokenizer(lineValue);
        while (st.hasMoreTokens()) {
            //获取分割之后的值
            String wordValue = st.nextToken();
            //设置map
            word.set(wordValue);
            //设置到上下文中传给reduce
            context.write(new Text("1"), one);
        }
    }
}
