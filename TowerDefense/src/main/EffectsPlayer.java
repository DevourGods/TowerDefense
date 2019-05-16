package main;

import java.io.IOException;
import javax.sound.sampled.*;

public class EffectsPlayer {
	
	static Clip clip = null;
	AudioInputStream audioIn;
	
	public EffectsPlayer() { // Loads sound
		try {
			audioIn = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/resources/death.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			audioIn.close();
		} catch (UnsupportedAudioFileException uafe) {
			uafe.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(LineUnavailableException lue) {
			lue.printStackTrace();
		}
	}

}
