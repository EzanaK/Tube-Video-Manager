package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.xml.stream.events.Comment;

import org.junit.Test;

import tubeVideosManager.Genre;
import tubeVideosManager.Playlist;
import tubeVideosManager.TubeVideosManager;
import tubeVideosManager.Video;

/**
 * 
 * You need student tests if you are asking for help during office hours about
 * bugs in your code. Feel free to use tools available in TestingSupport.java
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {

	@Test
	public void testPlaylistConstructors() {
		Playlist p1 = new Playlist("First Playlist");
		Playlist p2 = new Playlist(p1);
		System.out.println("Before changes to p2:");
		System.out.println("p1: " + p1.toString() + "\np2: " + p2.toString());
		p2.addToPlaylist("title1");
		p2.addToPlaylist("title2");
		System.out.println("\nAfter changes to p2:");
		System.out.println("p1: " + p1.toString() + "\np2: " + p2.toString());
		assertTrue(!p1.toString().equals(p2.toString()));
	}

	@Test
	public void testGetName() {
		Playlist p1 = new Playlist("First Playlist");
		System.out.println(p1.getName());
		assertTrue(p1.getName().equals("First Playlist"));
	}

	@Test
	public void testAddToPlaylist() {
		Playlist p1 = new Playlist("First Playlist");
		System.out.println(p1.toString());
		p1.addToPlaylist("First Title");
		System.out.println(p1.toString());
		ArrayList<String> list = p1.getPlaylistVideosTitles();
		assertTrue(list.get(0).equals("First Title"));
	}

	@Test
	public void testGetPlaylistVideosTitles() {
		Playlist p1 = new Playlist("First Playlist");
		p1.addToPlaylist("First Title");
		p1.addToPlaylist("Second Title");
		p1.addToPlaylist("Third Title");
		p1.addToPlaylist("Fourth Title");
		p1.addToPlaylist("Fifth Title");
		ArrayList<String> list = p1.getPlaylistVideosTitles();
		list.set(2, "changed");
		System.out.println("p1:\n" + p1.toString() + "\n\nlist: " + list);
		assertTrue(list.get(0).equals("First Title"));
		assertTrue(list.get(1).equals("Second Title"));
		assertTrue(list.get(2).equals("changed"));
		assertTrue(list.get(3).equals("Fourth Title"));
		assertTrue(list.get(4).equals("Fifth Title"));
	}

	@Test
	public void testRemoveFromPlaylistAll() {
		Playlist p1 = new Playlist("First Playlist");
		p1.addToPlaylist("First Title");
		p1.addToPlaylist("Target");
		p1.addToPlaylist("Third Title");
		p1.addToPlaylist("Target");
		p1.addToPlaylist("Fifth Title");
		p1.removeFromPlaylistAll("Target");
		System.out.println(p1.toString());
		ArrayList<String> list = p1.getPlaylistVideosTitles();
		assertTrue(list.size() == 3);
	}

	@Test
	public void testShuffleVideoTitles() {
		Playlist p1 = new Playlist("First Playlist");
		p1.addToPlaylist("First Title");
		p1.addToPlaylist("Second Title");
		p1.addToPlaylist("Third Title");
		p1.addToPlaylist("Fourth Title");
		p1.addToPlaylist("Fifth Title");
		p1.shuffleVideoTitles(new Random());
		System.out.println(p1.toString());
		p1.shuffleVideoTitles(new Random());
		System.out.println(p1.toString());
		p1.shuffleVideoTitles(new Random());
		System.out.println(p1.toString());
	}

	@Test
	public void testVideoConstructor() {
		Video v1 = new Video("Video1", "www.video1.com", 45, Genre.Comedy);
		Video v2 = new Video(v1);
		System.out.println("v1:\n" + v1.toString() + "\nv2:\n" + v2.toString());
		assertTrue(v1.toString().equals(v2.toString()));
	}

	@Test
	public void testVideoGetMethods() {
		Video v1 = new Video("Video1", "www.video1.com", 45, Genre.Comedy);
		System.out.println("title: " + v1.getTitle());
		assertTrue(v1.getTitle().equals("Video1"));
		System.out.println("url: " + v1.getUrl());
		assertTrue(v1.getUrl().equals("www.video1.com"));
		System.out.println("duration in minutes: " + v1.getDurationInMinutes());
		assertTrue(v1.getDurationInMinutes() == 45);
		System.out.println("Genre: " + v1.getGenre());
		assertTrue(v1.getGenre().name().equals("Comedy"));
	}

	@Test
	public void testAddGetComments() {
		Video v1 = new Video("Video1", "www.video1.com", 45, Genre.Comedy);
		v1.addComments("good video");
		v1.addComments("bad video");
		v1.addComments("great video");
		ArrayList<String> list = v1.getComments();
		System.out.println(list);
	}

	@Test
	public void testCompareTo() {
		Video v1 = new Video("Banana", "www.banana.com", 45, Genre.Comedy);
		Video v2 = new Video("Strawberry", "www.strawberry.com", 60, Genre.Documentary);
		Video v3 = new Video("Apple", "www.apple.com", 15, Genre.Educational);
		ArrayList<Video> list = new ArrayList<>();
		list.add(v1);
		list.add(v2);
		list.add(v3);
		System.out.println("Before sorting:\n" + list);
		Collections.sort(list);
		System.out.println("\nAfter sorting:\n" + list);
	}

	@Test
	public void testEquals() {
		Video v1 = new Video("Video1", "www.video1.com", 45, Genre.Comedy);
		Video v2 = new Video(v1);
		assertTrue(v1.equals(v2));
		Video v3 = new Video("Apple", "www.apple.com", 15, Genre.Educational);
		assertFalse(v1.equals(v3));
		String str1 = new String();
		assertFalse(v1.equals(str1));
		String str2 = null;
		assertFalse(v1.equals(str2));
	}

	@Test
	public void testTubeVideoManagerConstructorAndAddVideoToDB() {
		TubeVideosManager manager = new TubeVideosManager();
		boolean addVideo = manager.addVideoToDB("video1", "www.video1.com", 30, Genre.Documentary);
		assertTrue(addVideo);
		addVideo = manager.addVideoToDB("", null, 0, Genre.Comedy);
		assertFalse(addVideo);
	}

	@Test
	public void testGetAllVideosInDB() {
		TubeVideosManager manager = new TubeVideosManager();
		manager.addVideoToDB("video1", "www.video1.com", 30, Genre.Documentary);
		manager.addVideoToDB("video2", "www.video2.com", 45, Genre.Comedy);
		manager.addVideoToDB("video3", "www.video3.com", 15, Genre.Educational);
		ArrayList<Video> list = manager.getAllVideosInDB();
		System.out.println(list);
		assertTrue(list.get(1).getTitle().equals("video2"));
	}

	@Test
	public void testFindVideo() {
		TubeVideosManager manager = new TubeVideosManager();
		manager.addVideoToDB("video1", "www.video1.com", 30, Genre.Documentary);
		manager.addVideoToDB("video2", "www.video2.com", 45, Genre.Comedy);
		manager.addVideoToDB("video3", "www.video3.com", 15, Genre.Educational);
		assertTrue(manager.findVideo("video5") == null);
		assertTrue(manager.findVideo("video3").getTitle().equals("video3"));
	}

	@Test
	public void testAddComments() {
		TubeVideosManager manager = new TubeVideosManager();
		manager.addVideoToDB("video1", "www.video1.com", 30, Genre.Documentary);
		manager.addVideoToDB("video2", "www.video2.com", 45, Genre.Comedy);
		manager.addVideoToDB("video3", "www.video3.com", 15, Genre.Educational);
		boolean addComments = manager.addComments("video2", "Comment for video 2");
		assertTrue(addComments);
		System.out.println(manager.getAllVideosInDB().get(1).getComments());
		addComments = manager.addComments("video10", "Comment for video 10");
		assertFalse(addComments);
		addComments = manager.addComments("", "Comment for video");
		assertFalse(addComments);
	}

	@Test
	public void testAddPlaylist() {
		TubeVideosManager manager = new TubeVideosManager();
		boolean addPlaylist = manager.addPlaylist("playlist1");
		assertTrue(addPlaylist);
		addPlaylist = manager.addPlaylist("playlist2");
		assertTrue(addPlaylist);
		addPlaylist = manager.addPlaylist("playlist1");
		assertFalse(addPlaylist);
	}

	@Test
	public void testGetPlaylistsNames() {
		TubeVideosManager manager = new TubeVideosManager();
		manager.addPlaylist("playlist1");
		manager.addPlaylist("playlist2");
		manager.addPlaylist("playlist3");
		String[] strArray = manager.getPlaylistsNames();
		int i;
		for (i = 0; i < strArray.length; i++) {
			System.out.println(strArray[i]);
		}
	}

	@Test
	public void testAddVideoToPlaylist() {
		TubeVideosManager manager = new TubeVideosManager();
		assertFalse(manager.addVideoToPlaylist("video1", "playlist1"));
		manager.addPlaylist("playlist1");
		assertFalse(manager.addVideoToPlaylist("video1", "playlist1"));
		manager.addVideoToDB("video1", "www.video1.com", 30, Genre.Documentary);
		assertFalse(manager.addVideoToPlaylist("video1", "playlist2"));
		assertTrue(manager.addVideoToPlaylist("video1", "playlist1"));
	}

	@Test
	public void testGetPlaylist() {
		TubeVideosManager manager = new TubeVideosManager();
		Playlist playlist = manager.getPlaylist("playlist1");
		assertTrue(playlist == null);
		manager.addPlaylist("playlist1");
		manager.addVideoToDB("video1", "www.video1.com", 30, Genre.Documentary);
		manager.addVideoToDB("video2", "www.video2.com", 45, Genre.Comedy);
		manager.addVideoToDB("video3", "www.video3.com", 15, Genre.Educational);
		manager.addVideoToPlaylist("video1", "playlist1");
		manager.addVideoToPlaylist("video2", "playlist1");
		manager.addVideoToPlaylist("video3", "playlist1");
		playlist = manager.getPlaylist("playlist1");
		System.out.println(playlist.toString());
	}

	@Test
	public void testClearDatabase() {
		TubeVideosManager manager = new TubeVideosManager();
		manager.addPlaylist("playlist1");
		manager.addPlaylist("playlist2");
		manager.addPlaylist("playlist3");
		manager.addVideoToDB("video1", "www.video1.com", 30, Genre.Documentary);
		manager.addVideoToDB("video2", "www.video2.com", 45, Genre.Comedy);
		manager.addVideoToDB("video3", "www.video3.com", 15, Genre.Educational);
		manager.addVideoToPlaylist("video1", "playlist1");
		manager.addVideoToPlaylist("video2", "playlist2");
		manager.addVideoToPlaylist("video3", "playlist3");
		manager.clearDatabase();
		assertTrue(manager.getAllVideosInDB().size() == 0);
		assertTrue(manager.getPlaylistsNames().length == 0);
	}

	@Test
	public void testSearchForVideos() {
		TubeVideosManager manager = new TubeVideosManager();
		manager.addVideoToDB("video1", "www.video1.com", 30, Genre.Documentary);
		manager.addVideoToDB("video2", "www.video2.com", 45, Genre.Comedy);
		manager.addVideoToDB("video3", "www.video3.com", 15, Genre.Educational);
		Playlist playlist = manager.searchForVideos("New Playlist1", null, -1, null);
		assertTrue(playlist.getName().equals("New Playlist1") && playlist.getPlaylistVideosTitles().size() == 3);
		playlist = manager.searchForVideos("New Playlist2", null, 35, null);
		assertTrue(playlist.getPlaylistVideosTitles().size() == 2);
		playlist = manager.searchForVideos("New Playlist3", null, 20, Genre.Comedy);
		assertTrue(playlist.getPlaylistVideosTitles().size() == 0);
		playlist = manager.searchForVideos("New Playlist4", "video3", 20, Genre.Educational);
		assertTrue(playlist.getPlaylistVideosTitles().get(0).equals("video3"));
	}

}
