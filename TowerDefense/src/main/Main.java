package main;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class Main {
	
	private JFrame mainFrame;
	private Dimension frameSize;
	public static ActionPanel actionPanel;
	public static SoundPlayer soundPlayer;
	public static GamePanel gamePanel;
	public static boolean isPaused = true;
	public static long clipTime = 0;
	public static EffectsPlayer effectsPlayer;
	
	private Main() {
		initializeVariables();
		initializeInterface();
	}
	
	private void initializeVariables() {
		mainFrame  = new JFrame("Undead Defense");
		frameSize = new Dimension(1280, 720);
		actionPanel = new ActionPanel(new Dimension(320, 720));
		gamePanel = new GamePanel(new Dimension(960, 720));
		soundPlayer = new SoundPlayer();
		effectsPlayer = new EffectsPlayer();
	}
	
	public static void start() {
		gamePanel.gameLoop = new Thread(gamePanel);
		gamePanel.gameLoop.start();
		isPaused = false;
		
		soundPlayer.clip.setMicrosecondPosition(clipTime);
		soundPlayer.clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public static void pause() {
		gamePanel.gameLoop = null;
		isPaused = true;
		
		clipTime = soundPlayer.clip.getMicrosecondPosition();
		soundPlayer.clip.stop();
	}
	
	private void initializeInterface() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);
		mainFrame.setSize(frameSize);
		mainFrame.setResizable(false);
		mainFrame.setFocusable(true);
		
		mainFrame.add(actionPanel);
		actionPanel.setBounds(0,0,320,720);
		
		mainFrame.add(gamePanel);
		gamePanel.setBounds(320,0,960,720);
		
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
