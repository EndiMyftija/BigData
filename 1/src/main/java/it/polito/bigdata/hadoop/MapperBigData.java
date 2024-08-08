package it.polito.bigdata.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


/**
 * Basic MapReduce Project - Mapper
 */
class MapperBigData extends Mapper<
                    LongWritable, // Input key type
                    Text,         // Input value type
                    Text,         // Output key type
                    IntWritable> {// Output value type
                        
    private static float PM10Threshold = 50;
    
    protected void map(
            Text key,   // Input key type
            Text value,         // Input value type
            Context context) throws IOException, InterruptedException {

            //Extract sensor and date from the key
            String[] fields = value.toString().split(",");
            String sensorId = fields[0];
            double PM10Level = Double.valueOf(fields[1].split("\\s+")[1]);

            if (PM10Level > PM10Threshold) {
                context.write(new Text(sensorId), new IntWritable(1));
            }
    }
}
