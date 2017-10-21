package com.flipkart.mr;

import com.flipkart.mr.mapper.AvgByGenderMapper;
import com.flipkart.mr.mapper.WordCountMapper;
import com.flipkart.mr.reducer.AvgByGenderReducer;
import com.flipkart.mr.reducer.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

public class AvgByGender {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration c=new Configuration();
        String[] files=new GenericOptionsParser(c,args).getRemainingArgs();
        Path input=new Path(files[0]);
        Path output=new Path(files[1]);
        Job j=new Job(c,"avgByGender");
        j.setJarByClass(AvgByGender.class);
        j.setMapperClass(AvgByGenderMapper.class);
        j.setReducerClass(AvgByGenderReducer.class);
        j.setOutputKeyClass(Text.class);
        j.setOutputValueClass(FloatWritable.class);
        FileInputFormat.addInputPath(j, input);
        FileOutputFormat.setOutputPath(j, output);
        System.exit(j.waitForCompletion(true)?0:1);
    }

}
