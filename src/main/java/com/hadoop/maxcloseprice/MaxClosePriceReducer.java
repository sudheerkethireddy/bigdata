package com.hadoop.maxcloseprice;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxClosePriceReducer 
extends Reducer<Text, FloatWritable, Text, FloatWritable>{
	
	@Override
	public void reduce(Text key,Iterable<FloatWritable> values,Context context)
		throws IOException,InterruptedException{
		
		float maxClosePrice = Float.MIN_VALUE;
		//now lets iterate through all the stock prices for  a share and calculate max
		for(FloatWritable value:values)
		{
			maxClosePrice = Math.max(maxClosePrice, value.get());
		}
		
		context.write(key, new FloatWritable(maxClosePrice));
	}

}
