package grooveSharkHandler;
import iTunesParser.iTunesParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class GrooviSearch {
	public static ArrayList<SongID> SID = new ArrayList<SongID>();
	//static final String iTunesXML = "C:\\Users\\Marco\\Desktop\\iTunes Music Library.xml";
	static final String iTunesXML = "/home/marco/Desktop/iTunes Music Library.xml";
	static final File SIDstorage = new File("/home/marco/Desktop/first39.txt");
	static GSAPIGEThandler urltest = new GSAPIGEThandler();
	static int emptyCount=0;
	static int suctries=0;
	static int successCount=0;
	static final int tryOptions=6;
	
	//kind of pointless because tinysong denies my request after like 30 some request :/
	
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException{
		iTunesParser.readFile(iTunesXML);
		//buildSongID(0,50);
		//Thread.sleep(5000);
		//save2File(SID, SIDstorage);
		readFromFile(SIDstorage);
		String songIDs = makeSongIDS(SID);
		urltest.makePlaylist("playTest5", songIDs);
	}	
	
	public static void buildSongID(int startValue, int endValue) throws MalformedURLException, IOException, InterruptedException{
		int maxSongs = iTunesParser.SONGS.size();
		for(int k = startValue;k<endValue+1;k++){
		 SongID	current = new SongID();
		current.name = iTunesParser.SONGS.get(k).returnSongName();
		current.artist = iTunesParser.SONGS.get(k).returnSongArtist();
		//current.album = iTunesParser.SONGS.get(k).returnSongAlbum().toLowerCase();
		current.album = "";
		System.out.println(current.name);
		String songID = urltest.openConnection(current.name, current.artist, current.album, 0);
		if(songID.length()<2){
			//loop to cycle through the try again
			tryAgain(current.name, current.artist, current.album);
		}
		if(songID.length()>2){
			successCount++;
		}
		current.SongID = songID;
		System.out.println(songID);
		System.out.println(k);
		System.out.println("There were: "+successCount+ " successful tries! out of " + (k+1));
		//System.out.println("There are: " + (k-emptyCount)+"Succesful SID");
		SID.add(current);
	//	if(k%21 == 0 & k>20){
			
		//	System.out.println("Sleeping for 5 min");
			//Thread.sleep(5*60000);
	//	}
		
		}
	}
	
	public static String tryAgain(String name, String artist, String album) throws MalformedURLException, IOException{
		String songID;
		for(int t=1;t<=tryOptions;t++){
			songID = urltest.openConnection(name, artist, album, t);
			if(songID.length()>2){
				System.out.println("Try Successful!");
				suctries++;
				return songID;
			}else{
			System.err.println("Unsuccessful try");
			emptyCount++;
			}
		}
		return "";
	}
	
	public static void save2File(ArrayList<SongID> SID, File filename) throws IOException{
		BufferedWriter out = new BufferedWriter(new FileWriter(filename));
		String line;
		for(int k=0; k<SID.size();k++){
			if(SID.get(k).returnSID().length()>2){
			line = SID.get(k).makeLine();
			out.write(line);
			out.newLine();
			}
		}
		out.close();
	}
	public static void readFromFile(File filename) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		String line;
		SongID current = new SongID();
		while((line=in.readLine())!=null){
			System.out.println(line);
		int temp = line.indexOf("#SONGID:");
		current.SongID = line.substring(temp+8);
		System.out.println(line.substring(temp+8));
		SID.add(current);
		}

	}

	public static String makeSongIDS(ArrayList<SongID> SID){
		String SongIDS="";
		for(int k=0; k<SID.size();k++){
			if(k==(SID.size()-1)){
				SongIDS=SongIDS+SID.get(k).returnSID();
			}else if(SID.get(k).returnSID().length()<2){
				System.out.println("No Song ID here!");
				emptyCount++;
			}else{
				SongIDS=SongIDS+SID.get(k).returnSID() + ",";
			}
			
		}
		return SongIDS;
	}
	
}
