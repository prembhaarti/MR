package com.flipkart.mr.reducer;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AvgByGenderReducer extends Reducer<Text,FloatWritable,Text,FloatWritable> {
    @Override
    protected void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
        Float total = 0f;
        Integer count = 0;
        try {
            for (FloatWritable score : values) {
                total += score.get();
                count++;
            }
            context.write(key, new FloatWritable(total / count));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
