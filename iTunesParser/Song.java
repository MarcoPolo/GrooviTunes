package iTunesParser;

import java.io.*;
import java.util.*;

public class Song {
	public int trackID;
	public String name;
	public String artist;
	public String album;
	public String genre;
	public String kind;
	public int size;
	public int time;
	public int trackNumber;
	public Date dModified;
	public Date dAdded;
	public int bitRate;
	public int sampleRate;
	public int playCount;
	public int playDate;
	public Date dPlay;
	public String persistentID;
	public String trackType;
	public String location;
	
	public void printSong() {
		IOHandler.print("trackID: " + trackID + "\n");
		IOHandler.print("name: " + name + "\n");
		IOHandler.print("artist: " + artist + "\n");
		IOHandler.print("album: " + album + "\n");
		IOHandler.print("genre: " + genre + "\n");
		IOHandler.print("kind: " + kind + "\n");
		IOHandler.print("calc'd size: " + calcSize() + "\n");
		IOHandler.print("size: " + getSize() + "\n");
		IOHandler.print("time: " + getTime() + "\n");
		IOHandler.print("trackNumber: " + trackNumber + "\n");
		IOHandler.print("bitRate: " + bitRate + "\n");
		IOHandler.print("sampleRate: " + sampleRate + "\n");
		IOHandler.print("playCount: " + playCount + "\n");
		IOHandler.print("playDate: " + playDate + "\n");
		IOHandler.print("persistentID: " + persistentID + "\n");
		IOHandler.print("trackType: " + trackType + "\n");
		IOHandler.print("location: " + location + "\n");
		IOHandler.print("\n");
	}
	
	public void printSongQuick(){
		IOHandler.print("name: " + name + "\n");
		IOHandler.print("artist: " + artist + "\n");
		IOHandler.print("album: " + album + "\n");
	}
	public String returnSongName(){
		return name;
	}
	
	public String returnSongArtist(){
		return artist;
	}
	
	public String returnSongAlbum(){
		return album;
	}
	
	public int getTime() { // in Seconds
		return time / 1000;
	}
	
	public int getSize() { // in Kbytes
		return size / 1024;
	}
	
	public int calcSize(int rate) { // Kbytes from time * param
		return (getTime() * rate) / 8;
	}
	
	public int calcSize() { // Kbytes from time * bitrate
		return (getTime() * bitRate) / 8;
	}
}