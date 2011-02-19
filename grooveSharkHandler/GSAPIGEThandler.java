package grooveSharkHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GSAPIGEThandler {
//this will open a connection to gsapi and ask to look up a song and then find the song ID for that song 
	//or maybe a better idea is to use tinysong for the songID search and let gsapi handle the playlist creation!
	//given the artist name the album name and the song name it will return the song ID
	String plus = "+";
	
	
	public String openConnection(String name, String artist, String album, int tryAgain) throws MalformedURLException, IOException{
		plus ="+";
		try{
		if(tryAgain==1){
			//tries again by ommiting the artist name in the search
			artist="";
			plus = "";
		}else if(tryAgain==2){
			
			//tries again by forcing all lower case in the artist name
			artist=artist.toLowerCase();
		}else if(tryAgain==3){
			artist=name;
		}else if(tryAgain==4){
			name=name.toLowerCase();
		}else if(tryAgain==5){
			name=name.toLowerCase();
			artist=artist.toLowerCase();
		}else if(tryAgain==6){
			name.toLowerCase();
			artist="";
			plus="";
		}
		URL tinySearch = new URL("http://tinysong.com/b/"+name+plus+artist);
		System.out.println(tinySearch);
		URLConnection tinyResult = tinySearch.openConnection();
		BufferedReader line = new BufferedReader(new InputStreamReader(tinyResult.getInputStream()));
		String wholeline;
		String SongidString="";
		String SongID;
	//	System.out.println(tinySearch);
		while((wholeline=line.readLine())!=null){
		//	System.out.println(wholeline);
			if(wholeline.length()<26){
			SongidString=";";	
			}else{
			SongidString=wholeline.substring(26);
			}
		}
		int endSID = SongidString.indexOf(";");
		SongID = SongidString.substring(0,endSID);
	//	System.out.println("The song ID is: "+ SongID);
		return SongID;
		}catch(NullPointerException e){
			System.err.println("NullPointerException :(");
			return "";
		}
	}
	//this opens the connection with the given songID's and adds them to GrooveShark to the playlist called playlistID
	public void makePlaylist(String playlistID, String songIDs) throws IOException{
		System.setProperty("http.agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		String apikey = "XXXXXXapiKey_taken_from_apisharkXXXXXX";
		URL makeURL = new URL("http://1.apishark.com/createPlaylist/"+apikey+"/"+playlistID+"/"+songIDs+"21771098"+"/");
		System.out.println(makeURL);
		URLConnection makeResult = makeURL.openConnection();
		makeResult.connect();
		BufferedReader line = new BufferedReader(new InputStreamReader(makeResult.getInputStream()));
		String stline;
		while((stline = line.readLine())!= null){
			System.out.println(stline);
		}
		
		
		
	}
	
}
