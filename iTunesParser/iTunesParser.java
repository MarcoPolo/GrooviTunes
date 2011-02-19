package iTunesParser;
import java.io.*;
import java.util.*;

//player choice stuff in method "getInputFromUser()"
public class iTunesParser {
	public static ArrayList<Song> SONGS = new ArrayList<Song>();
	static String[] songArray;
	
	public static void main(String args[]) {
		IOHandler.print("Welcome to the iTunesXMLParser v0.3.\nThis program will show you how much space encoding all your MP3s\nto M4As will save you.\n\n");
		if(args.length != 1 || args[0].equals("path goes here")) {
			IOHandler.print("Edit the batch file to contain the path to your iTunes Library XML file (usually under the My Music/iTunes folder).\n");
			return;
		}
		String fileName = args[0];
		readFile(fileName);
		int realsize = 0;
		int sum = 0;
		int calc = 0;
		int fullcalc = 0;
		for(int i = 0; i < SONGS.size(); i++) {
			if(SONGS.get(i).bitRate > 128) {
				sum += SONGS.get(i).calcSize();
				calc += SONGS.get(i).calcSize(128);
			}
			fullcalc += SONGS.get(i).calcSize();
			realsize += SONGS.get(i).getSize();
		}
		double error = Math.abs(fullcalc - realsize) / realsize;
		IOHandler.print("Calc'd size: " + formatSize(fullcalc) + "\n");
		IOHandler.print("Real size: " + formatSize(realsize) + "\n");
		IOHandler.print("Percent of Error: " + error * 100 + "%\n");
		IOHandler.print("Calc'd >128 bit size: " + formatSize(sum) + "\n");
		IOHandler.print("Calc'd of recode: " + formatSize(calc) + "\n");
		IOHandler.print("Savings: " + formatSize(sum - calc) + "\n");
		Iterator songIterate = SONGS.iterator();
		SONGS.get(0).printSongQuick();
		System.out.println(SONGS.size());
		for(int k=0;k<SONGS.size();k++){
			SONGS.get(k).printSongQuick();
			System.out.println(k);
		}
		
	}
	
	public static String formatSize(int size) {
		String str = "";
		double kb = size;
		double mb = Math.round((kb / 1024) * 10000) / 10000;
		double gb = Math.round((mb / 1024) * 10000) / 10000;
		str += Integer.toString(size) + "KiB / ";
		str += Double.toString(mb) + "MiB / ";
		str += Double.toString(gb) + "GiB";
		return str;
	}
	
	
	public static void readFile(String fileName) {	
		BufferedReader in = null;
		try { 
			in = new BufferedReader(new FileReader(fileName));
		} catch(Exception e) {
			IOHandler.print("File not found! Did you forget to edit the batch file?\n");
			System.exit(1);
		}
		String line = "asdf";
		Song current = null;
		boolean started = false;
		
		while(line != null) {
			try {
				line = in.readLine();
				if(line.indexOf("<key>Tracks</key>") != -1) {
					started = true;
					in.readLine();
				}
				else if(!started);
				else if(line.indexOf("<key>Playlists</key>") != -1)
					break; // we're done with the songs
				else if(line.indexOf("<dict>") != -1)
					current = new Song();
				else if(line.indexOf("<key>Track ID</key>") != -1)
					current.trackID = Integer.parseInt(line.substring(line.indexOf("<integer>") + 9, line.indexOf("</i")));
				else if(line.indexOf("<key>Size</key>") != -1)
					current.size = Integer.parseInt(line.substring(line.indexOf("<integer>") + 9, line.indexOf("</i")));
				else if(line.indexOf("<key>Total Time</key>") != -1)
					current.time = Integer.parseInt(line.substring(line.indexOf("<integer>") + 9, line.indexOf("</i")));
				else if(line.indexOf("<key>Track Number</key>") != -1)
					current.trackNumber = Integer.parseInt(line.substring(line.indexOf("<integer>") + 9, line.indexOf("</i")));	
				else if(line.indexOf("<key>Bit Rate</key>") != -1)
					current.bitRate = Integer.parseInt(line.substring(line.indexOf("<integer>") + 9, line.indexOf("</i")));	
				else if(line.indexOf("<key>Sample Rate</key>") != -1)
					current.sampleRate = Integer.parseInt(line.substring(line.indexOf("<integer>") + 9, line.indexOf("</i")));	
				else if(line.indexOf("<key>Play Count</key>") != -1)
					current.playCount = Integer.parseInt(line.substring(line.indexOf("<integer>") + 9, line.indexOf("</i")));	
				else if(line.indexOf("<key>Play Date</key>") != -1)
					current.playDate = Integer.parseInt(line.substring(line.indexOf("<integer>") + 9, line.indexOf("</i")));	
				else if(line.indexOf("<key>Name</key>") != -1)
					current.name = line.substring(line.indexOf("<string>") + 8, line.indexOf("</s"));
				else if(line.indexOf("<key>Artist</key>") != -1)
					current.artist = line.substring(line.indexOf("<string>") + 8, line.indexOf("</s"));
				else if(line.indexOf("<key>Album</key>") != -1)
					current.album = line.substring(line.indexOf("<string>") + 8, line.indexOf("</s"));
				else if(line.indexOf("<key>Genre</key>") != -1)
					current.genre = line.substring(line.indexOf("<string>") + 8, line.indexOf("</s"));
				else if(line.indexOf("<key>Kind</key>") != -1)
					current.kind = line.substring(line.indexOf("<string>") + 8, line.indexOf("</s"));
				else if(line.indexOf("<key>Persistent ID</key>") != -1)
					current.persistentID = line.substring(line.indexOf("<string>") + 8, line.indexOf("</s"));
				else if(line.indexOf("<key>Track Type</key>") != -1)
					current.trackType = line.substring(line.indexOf("<string>") + 8, line.indexOf("</s"));
				else if(line.indexOf("<key>Location</key>") != -1)
					current.location = line.substring(line.indexOf("<string>") + 8, line.indexOf("</s"));
				else if(line.indexOf("</dict>") != -1)
					SONGS.add(current);
			} catch(Exception e) {}
		}
	}
}
