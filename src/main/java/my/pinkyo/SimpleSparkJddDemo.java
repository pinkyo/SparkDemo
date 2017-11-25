package my.pinkyo;

/* SimpleSparkJddDemo.java */

import com.google.common.collect.Iterators;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

public class SimpleSparkJddDemo {
    public static void main(String[] args) {
    	if (args.length < 1) {
    		System.err.println("you must specify a file path.");
    	}
    	
        String logFile = args[0]; // Should be some file on your system
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        long startTime = System.currentTimeMillis();
        System.out.println("==== start ====, time: " + startTime);
        
        JavaRDD<String> logData = sc.textFile(logFile).cache();
        
        JavaPairRDD<String, WordCount> numOfJava;
        numOfJava = logData.flatMap(line -> Arrays.stream(line.split("\\s"))
                .map(word -> new WordCount(word, 1L))
                .collect(Collectors.toList()).iterator())
                .keyBy(WordCount::getWord)
                .reduceByKey((wordCount1, wordCount2) ->
                        new WordCount(wordCount1.getWord(), wordCount1.getCount() + wordCount2.getCount()));
        System.out.println(numOfJava);
        System.out.println("line with node, case insensitive: " + numOfJava);

        long stopTime = System.currentTimeMillis();
        System.out.println("==== stop ====, time: " + stopTime);

        System.out.println("Time used: " + (stopTime - startTime));

        sc.stop();
    }

    public static class WordCount {
        private String word;
        private long count;

        public WordCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }
    }
}