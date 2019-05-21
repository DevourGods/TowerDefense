package main;

import java.io.IOException;
import javax.sound.sampled.*;

public class MusicPlayer{
	
	Clip clip = null;
	AudioInputStream audioIn;
	public Thread soundLoop = null;
	
	public MusicPlayer() {
		// Load the sound
		try{
			audioIn = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/resources/music.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			audioIn.close();
		}catch(UnsupportedAudioFileException uafe) {
			uafe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(LineUnavailableException lue) {
			lue.printStackTrace();
		}
	}
	
}