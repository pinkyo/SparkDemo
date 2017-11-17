package my.pinkyo;

/* SimpleSparkJddDemo.java */

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SimpleSparkJddDemo {
    public static void main(String[] args) {
        String logFile = "C:\\data\\test.log"; // Should be some file on your system
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> logData = sc.textFile(logFile).cache();

        long startTime = System.currentTimeMillis();
        System.out.println("==== start ====, time: " + startTime);
        long numOfDistinct = logData.distinct().count();
        long numOfJava = logData.filter(v1 -> v1.toLowerCase().contains("java")).count();

        System.out.println("distinct words: " + numOfDistinct);
        System.out.println("line with java, case insensitive: " + numOfJava);

        long stopTime = System.currentTimeMillis();
        System.out.println("==== stop ====, time: " + stopTime);

        System.out.println("Time used: " + (stopTime - startTime));

        sc.stop();
    }
}