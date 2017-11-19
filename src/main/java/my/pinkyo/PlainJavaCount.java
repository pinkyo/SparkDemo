package my.pinkyo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PlainJavaCount {

	public static void main(String[] args) {
		if (args.length < 1) {  
			System.err.println("you must specify a file path");
		}
		
		String sourceFilePath = args[0];
		
		 long startTime = System.currentTimeMillis();
		 System.out.println("==== start ====, time: " + startTime);
	        
		for (int i = 0; i < 20; i++) {
			try (BufferedReader sourceReader = new BufferedReader(new FileReader(sourceFilePath))) {
				int count = 0;

				String tmp = null;
				while ((tmp = sourceReader.readLine()) != null) {
					if (tmp.toLowerCase().contains("java"))
						count += 1;
				}

				System.out.println("line with java: " + count);

				long stopTime = System.currentTimeMillis();
				System.out.println("==== stop ====, time: " + stopTime);

				System.out.println("Time used: " + (stopTime - startTime));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		}

	}

}
