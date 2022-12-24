package SoldTogether;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SoldTogetherMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
    SoldTogetherMapper() {
        System.out.println("SoldTogetherMapper()");
    }

    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
            throws java.io.IOException {
        String data[] = value.toString().split(",");
        String items = data[data.length - 1];
        String itemarray[] = items.split(":");
        for (int i = 0; i < itemarray.length; i++) {
            for (int j = i + 1; j < itemarray.length; j++) {
                String word1 = itemarray[i];
                String word2 = itemarray[j];
                if (word2.compareTo(word1) > 0) {
                    output.collect(new Text(word2 + "," + word1), new IntWritable(1));
                } else {
                    output.collect(new Text(word1 + "," + word2), new IntWritable(1));
                }
            }
        }
    }
}
