package com.flipkart.mr.mapper;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

public class AvgByGenderMapper extends Mapper<LongWritable,Text,Text,FloatWritable>{

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words=value.toString().split(",");
//        System.out.println(Arrays.toString(words));
        String gender=words[3];
        String score=words[4];
        Text mapKey = new Text(gender);
        try {
            Float mapValue = new Float(score);
            context.write(mapKey, new FloatWritable(mapValue));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
