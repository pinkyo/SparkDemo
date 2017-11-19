package my.pinkyo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogDataGenerator {
	public static void main(String[] args) throws IOException {
		if (args.length < 2) {  
			System.err.println("you must specify a source file path and a target file path");
		}
		
		String sourceFilePath = args[0];
		String targetFilePath = args[1];
		
		Path targetPath = Paths.get(targetFilePath);
		if (Files.exists(targetPath) && Files.isRegularFile(targetPath)) {
			Files.delete(targetPath);
		}
		Files.createFile(targetPath);
		
		try(BufferedReader sourceReader = new BufferedReader(new FileReader(sourceFilePath));
				PrintWriter targetWriter = new PrintWriter(new FileOutputStream(targetFilePath))) {
			StringBuffer sb = new StringBuffer();
			String tmp = null;
			while ((tmp = sourceReader.readLine()) != null) {
				sb.append(tmp + "\n");
			}
			
			String result = sb.toString();
			
			for (int i = 0; i < 10; i++) {
				targetWriter.println(result);
			}
			
			targetWriter.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

}
