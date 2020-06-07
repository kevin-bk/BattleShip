package BatteShip;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
//import sun.

public class PlayMusic {
	
	public static void Play(String url) {
		InputStream music;
		try {
			music = new FileInputStream(new File(url));
//			AudioStream audio = new AudioStream(music);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
