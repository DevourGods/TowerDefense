package main;

import java.io.IOException;
import javax.sound.sampled.*;

public class SoundPlayer{
	
	static Clip clip = null;
	AudioInputStream audioIn;
	public Thread soundLoop = null;
	
	public SoundPlayer() {
		//load the sound
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