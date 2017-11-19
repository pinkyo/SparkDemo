package my.pinkyo;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class SimpleSparkSqlDemo {
    public static void main(String[] args) {
    	if (args.length < 1) {
    		System.err.println("you must specify a file path.");
    	}
    	
        String logFile = args[0]; // Should be some file on your system
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        
        long startTime = System.currentTimeMillis();
        System.out.println("==== start ====, time: " + startTime);
        Dataset<String> logData = spark.read().textFile(logFile).cache();

        
        for (int i = 0; i < 20; i++) {
			long numOfJava = logData.filter(v1 -> v1.toLowerCase().contains("java")).count();
			System.out.println("line with java, case insensitive: " + numOfJava);
			long stopTime = System.currentTimeMillis();
			System.out.println("==== stop ====, time: " + stopTime);
			System.out.println("Time used: " + (stopTime - startTime));
		}
		spark.stop();
    }
}
