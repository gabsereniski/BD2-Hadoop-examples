package SoldTogether;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SoldTogetherReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
    List<Item> itemList = new ArrayList<>();

    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output,
            Reporter reporter)
            throws IOException {

        int sum = 0;
        while(values.hasNext()) {
            IntWritable value = (IntWritable) values.next();
            sum += value.get();
        }

        Item item = new Item(key.toString(), sum);
        itemList.add(item);
        output.collect(new Text(key.toString()), new IntWritable(sum));
    }
	
    public void cleanup(OutputCollector output) throws IOException, InterruptedException {
        Collections.sort(itemList);
        System.out.println(itemList);
        for (Item item : itemList) 
        {
            output.collect(new Text(item.getName()), new IntWritable(item.getOccurance()));
        }
    }
   
}
