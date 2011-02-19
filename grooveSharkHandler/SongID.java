package grooveSharkHandler;

public class SongID {
	//this class is going to handle the song IDs and names
public String SongID;
public String artist;
public String name;
public String album;

public String makeLine(){
	String line = "#NAME=" + name + "#ARTIST=" + artist + " #SONGID:" + SongID;
	return line;
}

public String returnSID(){
	return SongID;
}

}
