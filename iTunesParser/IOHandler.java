package iTunesParser;

import java.io.*;

public class IOHandler {
	public static void print(String input) {
		System.out.print(input);
	}
	
	public static void printerr(String input) {
		System.err.print(input);
	}
	
	public static String get() {
		String input = new String();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			input = in.readLine();
		}
		catch (IOException e) {}
		return input;
	}
	
	public static String safesubs(String input, int start, int end) {
		if(input.length() < (end-start)) 
			return "";
		else
			return input.substring(start,end);
	}
	public static String getToEnd(String input, String subs) {
		int index = input.lastIndexOf(subs);
		if(index==-1)
			return null;
		return input.substring(index+subs.length(),input.length());
	}
}

