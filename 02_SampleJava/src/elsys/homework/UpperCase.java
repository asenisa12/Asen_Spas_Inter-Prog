package elsys.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UpperCase {
	
	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(input);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		String line;
		while( !(line = reader.readLine()).equals("end")) {
			System.out.println(line.toUpperCase());;
		}
	}
}
