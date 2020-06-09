package BatteShip;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
 
public class Sound {
	public JFrame frame;
   public Sound() {
	  frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Test Sound Clip");
      frame.setSize(300, 200);
      frame.setVisible(true);
      
      playSound("/sound/winner.wav");
   }
 
   private void playSound(String link) {
	      try {
	          // Open an audio input stream.
	          URL url = this.getClass().getResource(link);
	          AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	          // Get a sound clip resource.
	          Clip clip = AudioSystem.getClip();
	          // Open audio clip and load samples from the audio input stream.
	          clip.open(audioIn);
	          clip.start();
	       } catch (UnsupportedAudioFileException e) {
	          e.printStackTrace();
	       } catch (IOException e) {
	          e.printStackTrace();
	       } catch (LineUnavailableException e) {
	          e.printStackTrace();
	       }
   }
   
   public static void main(String[] args) {
      new Sound();
   }
}